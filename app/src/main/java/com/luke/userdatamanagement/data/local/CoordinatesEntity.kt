package com.luke.userdatamanagement.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "coordinates",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["uuid"],
        childColumns = ["userUuid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CoordinatesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userUuid: String,
    val latitude: String,
    val longitude: String
)
