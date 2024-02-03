package com.onestorecorp.gaa.prography.data.repsoitory

import androidx.paging.PagingData
import com.onestorecorp.gaa.prography.data.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
     fun fetchPhotoList(): Flow<PagingData<Photo>>

    suspend fun fetchPhotoDetail(id: String): Photo
}




