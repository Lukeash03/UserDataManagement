package com.luke.userdatamanagement.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "timezone",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["uuid"],
        childColumns = ["userUuid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class TimezoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userUuid: String,
    val offset: String,
    val description: String
)
