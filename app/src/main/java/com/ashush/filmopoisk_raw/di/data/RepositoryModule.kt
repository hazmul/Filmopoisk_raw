package com.ashush.filmopoisk_raw.di.data

import com.ashush.filmopoisk_raw.data.remote.RetrofitServiceProvider
import com.ashush.filmopoisk_raw.data.repository.DataRepositoryImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.datainterfaces.IDataRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideDataRepository(retrofit: RetrofitServiceProvider, storage: IStorage): IDataRepository {
        return DataRepositoryImpl(retrofit, storage)
    }
}

