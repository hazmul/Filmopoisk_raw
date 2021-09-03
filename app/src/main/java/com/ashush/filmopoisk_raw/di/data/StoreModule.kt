package com.ashush.filmopoisk_raw.di.data

import android.content.Context
import com.ashush.filmopoisk_raw.data.storage.IStorage
import com.ashush.filmopoisk_raw.data.storage.StorageImpl
import dagger.Module
import dagger.Provides

@Module
class StoreModule {
    @Provides
    fun provideStore(applicationContext: Context): IStorage {
        return StorageImpl(applicationContext)
    }
}