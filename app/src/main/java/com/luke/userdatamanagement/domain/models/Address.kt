package com.luke.userdatamanagement.domain.models

data class Address(
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Int
)
