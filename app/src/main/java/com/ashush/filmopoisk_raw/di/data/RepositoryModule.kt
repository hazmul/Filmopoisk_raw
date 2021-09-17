package com.ashush.filmopoisk_raw.di.data

import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.repository.RemoteRepositoryImpl
import com.ashush.filmopoisk_raw.data.repository.StorageRepositoryImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.data.storage.db.MovieDatabase
import com.ashush.filmopoisk_raw.domain.datainterfaces.IRemoteRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
import dagger.Module
import dagger.Provides

/**
 * Модуль зависимостей связанный с репозиториями
 */

@Module
class RepositoryModule {
    /**
     * Предоставление реализации интерфейса [IRemoteRepository]
     */
    @Provides
    fun provideDataRepository(retrofit: RetrofitServiceProvider, storage: IStorage): IRemoteRepository {
        return RemoteRepositoryImpl(retrofit, storage)
    }
    /**
     * Предоставление реализации интерфейса [IStorageRepository]
     */
    @Provides
    fun provideStorageRepository(storage: IStorage): IStorageRepository {
        return StorageRepositoryImpl(storage)
    }
}

