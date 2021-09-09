package com.ashush.filmopoisk_raw.di.data

import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.data.repository.ConfigRepositoryImpl
import com.ashush.filmopoisk_raw.data.repository.DataRepositoryImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.data_interfaces.IConfigRepository
import com.ashush.filmopoisk_raw.domain.data_interfaces.IDataRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideDataRepository(retrofit: RetrofitImpl, storage: IStorage): IDataRepository {
        return DataRepositoryImpl(retrofit, storage)
    }

    @Provides
    fun provideConfigRepository(storage: IStorage): IConfigRepository {
        return ConfigRepositoryImpl(storage)
    }
}

