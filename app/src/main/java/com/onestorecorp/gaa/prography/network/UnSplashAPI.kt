package com.onestorecorp.gaa.prography.network

import com.ddd.filmo.network.factory.ApiResult
import com.onestorecorp.gaa.prography.network.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashAPI {

    @GET("/photos")
    suspend fun fetchPhotoList(
        @Query("client_id") clientId: String = "u35wmblqV2tnoUNq74jUnKJPh5B_Z-au-qMhj8oGS8w",
        @Query("page") page: Int = 1,
    ): ApiResult<List<UnsplashResponse>>

    @GET("/photos")
    suspend fun fetchPhotoDetail(
        @Query("id") id: String,
    ): ApiResult<UnsplashResponse>
}
