package com.onestorecorp.gaa.prography.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onestorecorp.gaa.prography.data.mapper.toDomainList
import com.onestorecorp.gaa.prography.data.model.Photo
import com.onestorecorp.gaa.prography.data.remote.PhotoRemoteSource
import retrofit2.HttpException
import java.io.IOException

class PhotoPagingSource(
    private val remoteDataSource: PhotoRemoteSource,
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val currentPage = params.key ?: 1

            val photo = remoteDataSource.fetchPhotoList(currentPage)
            LoadResult.Page(
                data = photo.toDomainList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (photo.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

}