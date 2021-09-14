package com.ashush.filmopoisk_raw.data.storage

import android.content.Context
import com.ashush.filmopoisk_raw.data.storage.db.MovieDatabase
import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel
import com.google.gson.Gson
import javax.inject.Inject

class StorageImpl @Inject constructor(
    private val context: Context,
) : IStorage {

    companion object {
        private const val REMOTE_CONFIG_PREFS_KEY = "REMOTE_CONFIG_PREFS_KEY"
        private const val REMOTE_CONFIG_KEY = "REMOTE_CONFIG_KEY"
    }

    override fun storeRemoteConfiguration(config: DataConfigurationModel) {
        val gson = Gson()
        val json = gson.toJson(config)
        context.getSharedPreferences(REMOTE_CONFIG_PREFS_KEY, Context.MODE_PRIVATE).edit()
            .putString(REMOTE_CONFIG_KEY, json)
            .apply()
    }

    override fun getRemoteConfiguration(): DataConfigurationModel? {
        val gson = Gson()
        val json = context.getSharedPreferences(REMOTE_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
            .getString(REMOTE_CONFIG_KEY, null)
        return gson.fromJson(json, DataConfigurationModel::class.java)
    }

    override fun getFavoriteDao(): FavoritesDao {
        return MovieDatabase.getInstance(context).favoritesDao()
    }

    override fun getWatchlistDao(): WatchlistDao {
        return MovieDatabase.getInstance(context).watchlistDao()
    }

}

