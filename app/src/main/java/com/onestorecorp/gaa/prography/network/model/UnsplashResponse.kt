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
    //todo 시간이 좀더 있었다면 화면 크기에 따라 별개로 url 작업 했을것.
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,

    @SerialName("small_s3")
    val smallS3: String
)
