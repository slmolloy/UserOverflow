package com.scottmolloy.useroverflow

import androidx.lifecycle.MutableLiveData
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

object UsersApi {
    val httpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    private val url = "https://api.stackexchange.com/2.2/users"
    private val pageSize = 20

    fun getUsers(liveData: MutableLiveData<List<User>>) = runBlocking {
        val response: UserResponse = httpClient.get(url) {
            parameter("site", "stackoverflow")
            parameter("page", "1")
            parameter("pagesize", pageSize)
        }

        liveData.postValue(response.items)
    }
}