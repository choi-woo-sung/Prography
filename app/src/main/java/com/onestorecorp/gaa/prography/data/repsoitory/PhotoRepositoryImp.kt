package com.onestorecorp.gaa.prography.data.repsoitory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onestorecorp.gaa.prography.data.mapper.toDomain
import com.onestorecorp.gaa.prography.data.model.Photo
import com.onestorecorp.gaa.prography.data.paging.PhotoPagingSource
import com.onestorecorp.gaa.prography.data.remote.PhotoRemoteSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImp @Inject constructor(
    private val photoPagingSource: PhotoPagingSource,
    private val photoRemoteSource: PhotoRemoteSource
) : PhotoRepository {
    override fun fetchPhotoList(): Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 2),
        pagingSourceFactory = {
            photoPagingSource
        }
    ).flow

    override suspend fun fetchPhotoDetail(id: String): Photo =
        photoRemoteSource.fetchPhotoDetail(id).toDomain()
}