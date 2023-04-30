package com.df.ktorpexels.data.repository

import com.df.ktorpexels.data.models.DataVideos
import com.df.ktorpexels.data.network.PexelsHttpClient.Companion.BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val pexelsHttpClient: HttpClient
) : VideoRepository {

    override suspend fun getVideos(query: String): DataVideos {
        return pexelsHttpClient.get {
            url("${BASE_URL}/videos/search")
            parameter("query", query)
            parameter("per_page", 50)
            parameter("page", 1)
        }
    }
}