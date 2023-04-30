package com.df.ktorpexels.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataVideos(
    @SerialName("videos")
    val videos: List<VideoPexel> = listOf()
)

@Serializable
data class VideoPexel(
    @SerialName("id")
    val id: Int,
    @SerialName("duration")
    val duration: Int,
    @SerialName("image")
    val imageUrl: String,
    @SerialName("video_files")
    val videoFiles: List<VideoPexelLink> = listOf(),
    @SerialName("user")
    val user: User,
)

@Serializable
data class VideoPexelLink(
    @SerialName("link")
    val link: String = "link"
)

@Serializable
data class User(
    @SerialName("name")
    val name: String
)
