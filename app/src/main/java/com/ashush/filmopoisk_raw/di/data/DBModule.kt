package com.ashush.filmopoisk_raw.di.data

import android.content.Context
import androidx.room.Room
import com.ashush.filmopoisk_raw.data.db.DBRoom
import com.ashush.filmopoisk_raw.data.remote.RetrofitImpl
import com.ashush.filmopoisk_raw.data.storage.IStorage
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @Provides
    fun provideDB(applicationContext: Context): DBRoom {
        return Room.databaseBuilder(
            applicationContext,
            DBRoom::class.java, "filmopoisk-db"
        ).build()
    }
}

