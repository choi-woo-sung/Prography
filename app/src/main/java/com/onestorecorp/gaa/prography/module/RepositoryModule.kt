package com.ddd.filmo.core.module

import com.onestorecorp.gaa.prography.data.repsoitory.PhotoRepository
import com.onestorecorp.gaa.prography.data.repsoitory.PhotoRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// / TODO: 모듈을 하나 더 만들어서 여기서 주입을 시킬지.. 아니면 각 데이터 모듈에서 해야할지 고민중
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindPhotoRepository(photoRepositoryImp: PhotoRepositoryImp): PhotoRepository


}
