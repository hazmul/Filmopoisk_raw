package com.ashush.filmopoisk_raw.data.storage

import android.content.Context
import com.ashush.filmopoisk_raw.data.storage.db.MovieDatabase
import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.domain.config.DomainConfig
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.google.gson.Gson
import javax.inject.Inject

class StorageImpl @Inject constructor(
    private val context: Context,
) : IStorage {

    companion object {
        private const val REMOTE_CONFIG_PREFS_KEY = "REMOTE_CONFIG_PREFS_KEY"
        private const val REMOTE_CONFIG_KEY = "REMOTE_CONFIG_KEY"

        private const val DOMAIN_CONFIG_PREFS_KEY = "DOMAIN_CONFIG_PREFS_KEY"
        private const val CACHE_IMAGES_ALLOWED_KEY = "CACHE_ALLOWED_KEY"
        private const val DOWNLOAD_IMAGES_ALLOWED_KEY = "DOWNLOAD_IMAGES_ALLOWED_KEY"
        private const val THEME_TYPE_KEY = "THEME_TYPE_KEY"
        private const val ENABLE_NOTIFICATION_KEY = "ENABLE_NOTIFICATION_KEY"
        private const val RECYCLER_VIEW_TYPE_KEY = "RECYCLER_VIEW_TYPE_KEY"
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

    override fun storeDomainConfiguration(config: DomainConfig) {
        context.getSharedPreferences(DOMAIN_CONFIG_PREFS_KEY, Context.MODE_PRIVATE).edit()
            .putBoolean(CACHE_IMAGES_ALLOWED_KEY, config.cacheImageAllowed)
            .putBoolean(DOWNLOAD_IMAGES_ALLOWED_KEY, config.downloadImageAllowed)
            .putBoolean(THEME_TYPE_KEY, config.themeType)
            .putBoolean(ENABLE_NOTIFICATION_KEY, config.enableNotification)
            .putBoolean(RECYCLER_VIEW_TYPE_KEY, config.recyclerViewType)
            .apply()
    }

    override fun getDomainConfiguration(): DomainConfig {
        val prefs = context.getSharedPreferences(DOMAIN_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
        return DomainConfig(
            downloadImageAllowed = prefs.getBoolean(DOWNLOAD_IMAGES_ALLOWED_KEY, true),
            cacheImageAllowed = prefs.getBoolean(CACHE_IMAGES_ALLOWED_KEY, true),
            themeType = prefs.getBoolean(THEME_TYPE_KEY, false),
            enableNotification = prefs.getBoolean(ENABLE_NOTIFICATION_KEY, true),
            recyclerViewType = prefs.getBoolean(RECYCLER_VIEW_TYPE_KEY, false),
        )
    }

}