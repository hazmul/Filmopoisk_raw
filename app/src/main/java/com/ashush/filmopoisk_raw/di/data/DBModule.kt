package com.ashush.filmopoisk_raw.di.data

import android.content.Context
import androidx.room.Room
import com.ashush.filmopoisk_raw.data.storage.db.DBRoom
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

