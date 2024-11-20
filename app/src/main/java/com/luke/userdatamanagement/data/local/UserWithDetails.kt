package com.luke.userdatamanagement.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithDetails(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "userUuid"
    )
    val address: AddressEntity?,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "userUuid"
    )
    val coordinates: CoordinatesEntity?,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "userUuid"
    )
    val timezone: TimezoneEntity?,
    @Relation(
        parentColumn = "uuid",
        entityColumn = "userUuid"
    )
    val paymentDetails: PaymentDetailsEntity?
)
