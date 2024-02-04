package com.onestorecorp.gaa.prography.network

import com.ddd.filmo.network.factory.ApiResult
import com.onestorecorp.gaa.prography.network.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashAPI {
    


    @GET("/photos")
    suspend fun fetchPhotoList(
        @Query("client_id") clientId: String = photoclientId,
        @Query("page") page: Int = 1,
    ): ApiResult<List<UnsplashResponse>>

    @GET("/photos")
    suspend fun fetchPhotoDetail(
        @Query("id") id: String,
        @Query("client_id") clientId: String = photoclientId,
    ): ApiResult<UnsplashResponse>

    @GET("/photos/random")
    suspend fun fetchPhotoRandom(
        @Query("count") count: Int = 10,
        @Query("client_id") clientId: String = photoclientId,
    ): ApiResult<List<UnsplashResponse> >

    companion object{
        private val photoclientId: String = "ANR12F4KYM_oOprZesHpp9ydHMWH74nwpYbYIOb3g10"
    }
}
