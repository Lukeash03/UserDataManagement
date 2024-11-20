package com.luke.userdatamanagement.domain.models

data class User(
    val uuid: String,
    val name: String,
    val gender: String,
    val email: String,
    val username: String,
    val password: String,
    val phone: String,
    val cell: String,
    val dob: String,
    val age: Int,
    val registeredDate: String,
    val profilePictureUrl: String,
    val address: Address?,
    val coordinates: Coordinates?,
    val timezone: Timezone?,
    val paymentDetails: PaymentDetails?
)
