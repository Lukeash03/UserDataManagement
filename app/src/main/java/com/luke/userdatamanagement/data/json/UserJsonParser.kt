package com.luke.userdatamanagement.data.json

import com.luke.userdatamanagement.data.remote.dto.UserDto
import com.luke.userdatamanagement.domain.models.User
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class UserJsonParser @Inject constructor() : JsonParser<User> {
//    override suspend fun <T> parse(stream: InputStream): List<T> {
//        return withContext(Dispatchers.IO) {
//            val inputReader = InputStreamReader(stream)
//            val jsonString = inputReader.readText()
//            inputReader.close()
//
//            val moshi = Moshi.Builder().build()
//            val adapter = moshi.adapter(UserDto::class.java)
//
////                val jsonString = stream.bufferedReader().use { it.readText() }
//            adapter.fromJson(jsonString)
//        }
//    }
//}