package com.luke.userdatamanagement.data.remote

import com.luke.userdatamanagement.data.remote.dto.UserResponse
import retrofit2.http.GET

interface UserDataApi {

    @GET("api/?results=100&nat=IN")
    suspend fun getUserListings(): UserResponse

    companion object {
        const val BASE_URL = "https://randomuser.me"
    }
}