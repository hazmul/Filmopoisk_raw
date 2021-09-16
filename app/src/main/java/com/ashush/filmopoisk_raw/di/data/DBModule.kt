package com.ashush.filmopoisk_raw.di.data

import android.content.Context
import androidx.room.Room
import com.ashush.filmopoisk_raw.data.storage.db.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    private val dbname = "filmopoisk-db"

    @Singleton
    @Provides
    fun provideDB(applicationContext: Context): MovieDatabase {
        return Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java, dbname
        ).build()
    }
}

