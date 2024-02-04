package com.onestorecorp.gaa.prography.data.repsoitory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onestorecorp.gaa.prography.data.mapper.toDomain
import com.onestorecorp.gaa.prography.data.mapper.toDomainList
import com.onestorecorp.gaa.prography.data.model.Photo
import com.onestorecorp.gaa.prography.data.paging.PhotoPagingSource
import com.onestorecorp.gaa.prography.data.remote.PhotoRemoteSource
import com.onestorecorp.gaa.prography.network.model.UnsplashResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImp @Inject constructor(
    private val photoRemoteSource: PhotoRemoteSource
) : PhotoRepository {
    override fun fetchPhotoList(): Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 2),
        pagingSourceFactory = {
            PhotoPagingSource(photoRemoteSource)
        }
    ).flow

    override suspend fun fetchPhotoDetail(id: String): Photo =
        photoRemoteSource.fetchPhotoDetail(id).toDomain()

    override suspend fun fetchRandomPhotoList(): List<Photo> {
        return photoRemoteSource.fetchRandomPhotoList().toDomainList()
    }
}