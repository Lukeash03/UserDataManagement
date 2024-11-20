package com.luke.userdatamanagement.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val results: List<UserDto>
)

data class UserDto(
    val cell: String,
    val dob: DobDto,
    val email: String,
    val gender: String,
    val id: IdDto?,
    val location: LocationDto,
    val login: LoginDto,
    val name: NameDto,
    val nat: String,
    val phone: String,
    val picture: PictureDto,
    val registered: RegisteredDto
)

data class DobDto(
    val age: Int,
    val date: String
)

data class IdDto(
    val name: String,
    val value: String?
)

data class LocationDto(
    val city: String,
    val coordinates: CoordinatesDto,
    val country: String,
    val postcode: String,
    val state: String,
    val street: StreetDto,
    val timezone: TimezoneDto
)

data class LoginDto(
    val md5: String,
    val password: String,
    val salt: String,
    val sha1: String,
    val sha256: String,
    val username: String,
    val uuid: String
)

data class NameDto(
    val first: String,
    val last: String,
    val title: String
)

data class PictureDto(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class RegisteredDto(
    val age: Int,
    val date: String
)

data class CoordinatesDto(
    val latitude: String,
    val longitude: String
)

data class StreetDto(
    val name: String,
    val number: Int
)

data class TimezoneDto(
    val description: String,
    val offset: String
)