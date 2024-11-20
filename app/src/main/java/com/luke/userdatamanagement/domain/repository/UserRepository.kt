package com.luke.userdatamanagement.domain.repository

import com.luke.userdatamanagement.domain.models.User
import com.luke.userdatamanagement.ui.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<User>>>

    suspend fun getUserById(
        uuid: String
    ): Resource<User>

}