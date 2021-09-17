package com.ashush.filmopoisk_raw.di.domain

import com.ashush.filmopoisk_raw.domain.datainterfaces.IRemoteRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
import com.ashush.filmopoisk_raw.domain.interactor.Interactor
import dagger.Module
import dagger.Provides

/**
 * Модуль зависимостей связанный с слоем бизнес логики
 */

@Module
class DomainModule {
    /**
     * Предоставление сущности [Interactor]
     */
    @Provides
    fun provideInteractor(
        remoteRepository: IRemoteRepository,
        storageRepository: IStorageRepository
    ): Interactor {
        return Interactor(remoteRepository, storageRepository)
    }
}