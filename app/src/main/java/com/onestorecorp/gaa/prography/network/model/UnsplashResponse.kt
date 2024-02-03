package com.onestorecorp.gaa.prography.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashResponse(
    val id: String,
    val slug: String,
    val width: Long,
    val height: Long,
    val color: String,

    @SerialName("blur_hash")
    val blurHash: String,

    val description: String? = null,

    val urls: Urls,
    val likes: Long,

    @SerialName("liked_by_user")
    val likedByUser: Boolean,
)




@Serializable
data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,

    @SerialName("small_s3")
    val smallS3: String
)
