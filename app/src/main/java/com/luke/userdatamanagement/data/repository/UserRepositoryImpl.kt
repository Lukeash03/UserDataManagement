package com.luke.userdatamanagement.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.luke.userdatamanagement.data.local.UserDatabase
import com.luke.userdatamanagement.data.mapper.toDataModel
import com.luke.userdatamanagement.data.mapper.toDomain
import com.luke.userdatamanagement.data.remote.UserDataApi
import com.luke.userdatamanagement.domain.models.User
import com.luke.userdatamanagement.domain.repository.UserRepository
import com.luke.userdatamanagement.ui.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UserDataApi,
    private val db: UserDatabase
): UserRepository {

    private val dao = db.dao

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getUserListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchUserListing(query)
            val mappedLocalListings = localListings.map { userEntity ->
                val address = dao.getAddressByUserId(userEntity.uuid)
                val coordinates = dao.getCoordinatesByUserId(userEntity.uuid)
                val timezone = dao.getTimezoneByUserId(userEntity.uuid)
                val paymentDetails = dao.getPaymentDetailsByUserId(userEntity.uuid)

                userEntity.toDomain(
                    address = address,
                    coordinates = coordinates,
                    timezone = timezone,
                    paymentDetails = paymentDetails
                )
            }

            emit(Resource.Success(data = mappedLocalListings))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getUserListings()
                response.results
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                return@flow
            }

            val mappedRemoteListings = remoteListings.map { userDto ->
                userDto.toDomain()
            }
            dao.clearUserListings()
            dao.insertUserListings(
                mappedRemoteListings.map { it.toDataModel() }
            )

            emit(Resource.Success(mappedRemoteListings))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getUserById(uuid: String): Resource<User> {
        return try {
            val userEntity = dao.getUserById(uuid)
            val address = dao.getAddressByUserId(uuid)
            val coordinates = dao.getCoordinatesByUserId(uuid)
            val timezone = dao.getTimezoneByUserId(uuid)
            val paymentDetails = dao.getPaymentDetailsByUserId(uuid)

            if (userEntity != null) {
                val user = userEntity.toDomain(
                    address = address,
                    coordinates = coordinates,
                    timezone = timezone,
                    paymentDetails = paymentDetails
                )
                Resource.Success(data = user)
            } else {
                Resource.Error(message = "User not found")
            }
        } catch (e: Exception) {
            Resource.Error(message = "Error fetching user: ${e.message}")
        }
    }

}