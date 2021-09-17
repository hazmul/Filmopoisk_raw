package com.ashush.filmopoisk_raw.di.domain

import com.ashush.filmopoisk_raw.domain.datainterfaces.IRemoteRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideInteractor(
        remoteRepository: IRemoteRepository,
        storageRepository: IStorageRepository
    ): Interactor {
        return Interactor(remoteRepository, storageRepository)
    }
}