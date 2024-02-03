package com.onestorecorp.gaa.prography.network

import com.ddd.filmo.network.factory.ApiResult
import com.onestorecorp.gaa.prography.network.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashAPI {

    @GET("/photos")
    suspend fun fetchPhotoList(
        @Query("page") page: Int = 1,
    ): ApiResult<List<UnsplashResponse>>

    @GET("/photos")
    suspend fun fetchPhotoDetail(
        @Query("id") id: String,
    ): ApiResult<UnsplashResponse>
}
