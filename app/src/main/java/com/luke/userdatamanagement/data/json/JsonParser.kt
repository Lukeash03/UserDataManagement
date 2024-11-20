package com.luke.userdatamanagement.data.json

import java.io.InputStream

interface JsonParser<T> {
    suspend fun <T> parse(stream: InputStream): List<T>
}