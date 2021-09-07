package com.scottmolloy.useroverflow.data

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

object UsersApi {
    /**
     * Leaving httpClient here for simplicity.
     */
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
    private val pageSize = 50

    /**
     * Function for making api request. Page and pagesize parameters are not being fully utilized.
     */
    fun getUsers(): List<User> = runBlocking {
        val response: UserResponse = httpClient.get(url) {
            parameter("site", "stackoverflow")
            parameter("page", "1")
            parameter("pagesize", pageSize)
        }

        response.items
    }
}