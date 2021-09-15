package com.ashush.filmopoisk_raw.di.domain

import com.ashush.filmopoisk_raw.domain.datainterfaces.IDataRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideInteractor(
        dataRepository: IDataRepository,
        storageRepository: IStorageRepository
    ): Interactor {
        return Interactor(dataRepository, storageRepository)
    }
}