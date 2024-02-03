package com.onestorecorp.gaa.prography.data.mapper

import com.onestorecorp.gaa.prography.data.model.Photo
import com.onestorecorp.gaa.prography.network.model.UnsplashResponse


fun UnsplashResponse.toDomain(): Photo {
    return Photo(
        id = this.id,
        description = this.description ?: "",
        url = this.urls.thumb
    )
}


fun List<UnsplashResponse>.toDomainList(): List<Photo> {
    return this.map { it.toDomain() }
}

