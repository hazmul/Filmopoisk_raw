package com.ashush.filmopoisk_raw.di.data

import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.data.repository.RepositoryImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.domain.data_interfaces.IRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(retrofit: RetrofitImpl, storage: IStorage): IRepository {
        return RepositoryImpl(retrofit, storage)
    }
}

