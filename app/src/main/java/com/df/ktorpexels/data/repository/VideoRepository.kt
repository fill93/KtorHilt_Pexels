package com.df.ktorpexels.data.repository

import com.df.ktorpexels.data.models.DataVideos

interface VideoRepository {
    suspend fun getVideos(query: String): DataVideos
}