package com.luke.userdatamanagement.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserListings(
        userEntities: List<UserEntity>
    )

    @Query("DELETE FROM user")
    suspend fun clearUserListings()

    @Query(
        """
            SELECT *
            FROM user
            WHERE LOWER(firstName) LIKE '%' || LOWER(:query) || '%' OR
            UPPER(:query) == firstName
        """
    )
    suspend fun searchUserListing(query: String): List<UserEntity>

    @Query("SELECT * FROM address WHERE userUuid = :userId")
    suspend fun getAddressByUserId(userId: String): AddressEntity?

    @Query("SELECT * FROM coordinates WHERE userUuid = :userId")
    suspend fun getCoordinatesByUserId(userId: String): CoordinatesEntity?

    @Query("SELECT * FROM timezone WHERE userUuid = :userId")
    suspend fun getTimezoneByUserId(userId: String): TimezoneEntity?

    @Query("SELECT * FROM payment_details WHERE userUuid = :userId")
    suspend fun getPaymentDetailsByUserId(userId: String): PaymentDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaymentDetails(paymentDetailsEntity: PaymentDetailsEntity)

    @Query("SELECT * FROM user WHERE uuid = :uuid")
    suspend fun getUserById(uuid: String): UserEntity?

}