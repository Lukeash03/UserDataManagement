package com.luke.userdatamanagement.domain.models

data class PaymentDetails(
    val paymentMethod: String,
    val amount: Double,
    val isPaid: Boolean = false
)
