package com.luke.userdatamanagement.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "address",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["uuid"],
        childColumns = ["userUuid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AddressEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userUuid: String,
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Int
)
