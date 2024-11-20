package com.luke.userdatamanagement.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "payment_details",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["uuid"],
        childColumns = ["userUuid"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PaymentDetailsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userUuid: String,
    val paymentMethod: String,
    val amount: Double,
    val isPaid: Boolean = false
)
