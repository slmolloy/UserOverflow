package com.scottmolloy.useroverflow

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

object UsersApi {
    private val httpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.BODY
        }
    }

    private val url = "https://api.stackexchange.com/2.2/users"
    private val pageSize = 20

    fun getUsers(): List<User> = runBlocking {
        val response: UserResponse = httpClient.get(url) {
            parameter("site", "stackoverflow")
            parameter("page", "1")
            parameter("pagesize", pageSize)
        }

        response.items
    }
}