package com.luke.userdatamanagement.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserEntity::class,
        AddressEntity::class,
        CoordinatesEntity::class,
        TimezoneEntity::class,
        PaymentDetailsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao
}