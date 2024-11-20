package com.luke.userdatamanagement.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val uuid: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val username: String,
    val password: String,
    val phone: String,
    val cell: String,
    val dob: String,
    val age: Int,
    val registeredDate: String,
    val registeredAge: Int,
    val pictureLarge: String,
    val pictureMedium: String,
    val pictureThumbnail: String
)
