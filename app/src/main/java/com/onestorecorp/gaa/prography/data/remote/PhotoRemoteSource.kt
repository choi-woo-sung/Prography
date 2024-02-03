package com.onestorecorp.gaa.prography.data.remote

import com.ddd.filmo.network.factory.handlingNetwork
import com.onestorecorp.gaa.prography.network.UnSplashAPI
import com.onestorecorp.gaa.prography.network.model.UnsplashResponse
import javax.inject.Inject


interface PhotoRemoteSource {
    suspend fun fetchPhotoList(page: Int): List<UnsplashResponse>

    suspend fun fetchPhotoDetail(id: String): UnsplashResponse
}

class PhotoRemoteSourceImp @Inject constructor(
    private val unSplashAPI: UnSplashAPI
) : PhotoRemoteSource {
    override suspend fun fetchPhotoList(page: Int): List<UnsplashResponse> {
        return handlingNetwork { unSplashAPI.fetchPhotoList(page = page) }
    }

    override suspend fun fetchPhotoDetail(id: String): UnsplashResponse {
        return handlingNetwork { unSplashAPI.fetchPhotoDetail(id) }
    }
}