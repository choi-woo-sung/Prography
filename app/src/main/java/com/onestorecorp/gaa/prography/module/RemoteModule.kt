package com.onestorecorp.gaa.prography.module

import com.onestorecorp.gaa.prography.data.remote.PhotoRemoteSource
import com.onestorecorp.gaa.prography.data.remote.PhotoRemoteSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// / TODO: 모듈을 하나 더 만들어서 여기서 주입을 시킬지.. 아니면 각 데이터 모듈에서 해야할지 고민중
@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @Binds
    @Singleton
    fun bindPhotoRemoteSource(photoRemoteSourceImp: PhotoRemoteSourceImp): PhotoRemoteSource

}
