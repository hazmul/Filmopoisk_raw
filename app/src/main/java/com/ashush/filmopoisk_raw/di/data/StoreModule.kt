package com.ashush.filmopoisk_raw.di.data

import android.content.Context
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.data.storage.StorageImpl
import com.ashush.filmopoisk_raw.data.storage.db.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StoreModule {

    @Singleton
    @Provides
    fun provideStore(context: Context, movieDatabase: MovieDatabase): IStorage {
        return StorageImpl(context, movieDatabase)
    }
}