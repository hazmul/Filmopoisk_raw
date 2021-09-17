package com.ashush.filmopoisk_raw.di.data

import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.repository.RemoteRepositoryImpl
import com.ashush.filmopoisk_raw.data.repository.StorageRepositoryImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.datainterfaces.IRemoteRepository
import com.ashush.filmopoisk_raw.domain.datainterfaces.IStorageRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideDataRepository(retrofit: RetrofitServiceProvider, storage: IStorage): IRemoteRepository {
        return RemoteRepositoryImpl(retrofit, storage)
    }

    @Provides
    fun provideStorageRepository(storage: IStorage): IStorageRepository {
        return StorageRepositoryImpl(storage)
    }
}

