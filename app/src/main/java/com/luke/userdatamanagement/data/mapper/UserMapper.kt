package com.luke.userdatamanagement.data.mapper

import com.luke.userdatamanagement.data.local.AddressEntity
import com.luke.userdatamanagement.data.local.CoordinatesEntity
import com.luke.userdatamanagement.data.local.PaymentDetailsEntity
import com.luke.userdatamanagement.data.local.TimezoneEntity
import com.luke.userdatamanagement.data.local.UserEntity
import com.luke.userdatamanagement.data.remote.dto.CoordinatesDto
import com.luke.userdatamanagement.data.remote.dto.LocationDto
import com.luke.userdatamanagement.data.remote.dto.TimezoneDto
import com.luke.userdatamanagement.data.remote.dto.UserDto
import com.luke.userdatamanagement.domain.models.User
import com.luke.userdatamanagement.domain.models.Address
import com.luke.userdatamanagement.domain.models.Coordinates
import com.luke.userdatamanagement.domain.models.PaymentDetails
import com.luke.userdatamanagement.domain.models.Timezone

fun UserEntity.toDomain(
    address: AddressEntity?,
    coordinates: CoordinatesEntity?,
    timezone: TimezoneEntity?,
    paymentDetails: PaymentDetailsEntity?
): User {
    return User(
        uuid = this.uuid,
        name = "${this.title} ${this.firstName} ${this.lastName}",
        gender = this.gender,
        email = this.email,
        username = this.username,
        password = this.password,
        phone = this.phone,
        cell = this.cell,
        dob = this.dob,
        age = this.age,
        registeredDate = this.registeredDate,
        profilePictureUrl = this.pictureLarge,
        address = address?.toDomain(),
        coordinates = coordinates?.toDomain(),
        timezone = timezone?.toDomain(),
        paymentDetails = paymentDetails?.toDomain()
    )
}

fun AddressEntity.toDomain(): Address {
    return Address(
        streetNumber = this.streetNumber,
        streetName = this.streetName,
        city = this.city,
        state = this.state,
        country = this.country,
        postcode = this.postcode
    )
}

fun CoordinatesEntity.toDomain(): Coordinates {
    return Coordinates(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun TimezoneEntity.toDomain(): Timezone {
    return Timezone(
        offset = this.offset,
        description = this.description
    )
}

fun PaymentDetailsEntity.toDomain(): PaymentDetails {
    return PaymentDetails(
        paymentMethod = this.paymentMethod,
        amount = this.amount,
        isPaid = this.isPaid
    )
}

fun User.toDataModel(): UserEntity {
    return UserEntity(
        uuid = this.uuid,
        title = this.name.split(" ").firstOrNull() ?: "",
        firstName = this.name.split(" ").getOrNull(1) ?: "",
        lastName = this.name.split(" ").getOrNull(2) ?: "",
        gender = this.gender,
        email = this.email,
        username = this.username,
        password = this.password,
        phone = this.phone,
        cell = this.cell,
        dob = this.dob,
        age = this.age,
        registeredDate = this.registeredDate,
        registeredAge = 0, // Default value or calculate it if available
        pictureLarge = this.profilePictureUrl,
        pictureMedium = "", // Default or derive from pictureLarge if necessary
        pictureThumbnail = "" // Default or derive from pictureLarge if necessary
    )
}

fun UserDto.toDomain(): User {
    return User(
        uuid = this.login.uuid,
        name = "${this.name.first} ${this.name.last}",
//        ${this.name.title}
        gender = this.gender,
        email = this.email,
        username = this.login.username,
        password = this.login.password,
        phone = this.phone,
        cell = this.cell,
        dob = this.dob.date,
        age = this.dob.age,
        registeredDate = this.registered.date,
        profilePictureUrl = this.picture.large,
        address = this.location.toDomainAddress(),
        coordinates = this.location.coordinates.toDomainCoordinates(),
        timezone = this.location.timezone.toDomainTimezone(),
        paymentDetails = null
    )
}

fun LocationDto.toDomainAddress(): Address {
    return Address(
        streetNumber = this.street.number,
        streetName = this.street.name,
        city = this.city,
        state = this.state,
        country = this.country,
        postcode = this.postcode.toIntOrNull() ?: 0 // Handle potential string to int conversion
    )
}

fun CoordinatesDto.toDomainCoordinates(): Coordinates {
    return Coordinates(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun TimezoneDto.toDomainTimezone(): Timezone {
    return Timezone(
        offset = this.offset,
        description = this.description
    )
}
