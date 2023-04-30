package com.df.ktorpexels.data.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class PexelsHttpClient @Inject constructor()  {

    fun getHttpClient() = HttpClient(Android) {

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v(TAG_KTOR_LOGGER, message)
                }

            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(HttpHeaders.Authorization, "563492ad6f917000010000018872dac337224f058335f65b4b54d914")
        }
    }

    companion object {
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
        const val BASE_URL = "https://api.pexels.com"
    }
}