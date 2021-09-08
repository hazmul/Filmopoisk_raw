package com.ashush.filmopoisk_raw.di.data

import android.content.Context
import androidx.room.Room
import com.ashush.filmopoisk_raw.data.storage.db.MovieDatabase
import dagger.Module
import dagger.Provides

@Module
class DBModule {

    @Provides
    fun provideDB(applicationContext: Context): MovieDatabase {
        return Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java, "filmopoisk-db"
        ).build()
    }
}

