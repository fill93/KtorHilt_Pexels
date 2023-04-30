package com.df.ktorpexels.di

import com.df.ktorpexels.data.network.PexelsHttpClient
import com.df.ktorpexels.data.repository.VideoRepository
import com.df.ktorpexels.data.repository.VideoRepositoryImpl
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient

@InstallIn(SingletonComponent::class)
@Module
class AppModules {

    @Provides
    fun getPexelsHttpClient(pexelsHttpClient: PexelsHttpClient): HttpClient = pexelsHttpClient.getHttpClient()

    @Provides
    fun getVideoRepository(videoRepositoryImpl: VideoRepositoryImpl): VideoRepository = videoRepositoryImpl

    @Provides
    fun getRetString(): String = "String provides by hilt in class AppModule"

}