package com.ashush.filmopoisk_raw.data.storage

import android.content.Context
import androidx.preference.PreferenceManager
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.data.models.configuration.DataConfigurationModel
import com.ashush.filmopoisk_raw.data.storage.db.MovieDatabase
import com.ashush.filmopoisk_raw.data.storage.db.dao.FavoritesDao
import com.ashush.filmopoisk_raw.data.storage.db.dao.WatchlistDao
import com.ashush.filmopoisk_raw.domain.models.AppConfig
import com.google.gson.Gson
import javax.inject.Inject

class StorageImpl @Inject constructor(
    private val context: Context,
    private val movieDatabase: MovieDatabase,
) : IStorage {

    companion object {
        private const val REMOTE_CONFIG_PREFS_KEY = "REMOTE_CONFIG_PREFS_KEY"
        private const val REMOTE_CONFIG_KEY = "REMOTE_CONFIG_KEY"
        private const val APP_CONFIG_PREFS_KEY = "DOMAIN_CONFIG_PREFS_KEY"
        private const val APP_CONFIG_VIEWTYPE_KEY = "DOMAIN_CONFIG_VIEWTYPE_KEY"

    }

    override fun storeRemoteConfiguration(config: DataConfigurationModel): Boolean {
        val gson = Gson()
        val json = gson.toJson(config)
        context.getSharedPreferences(REMOTE_CONFIG_PREFS_KEY, Context.MODE_PRIVATE).edit()
            .putString(REMOTE_CONFIG_KEY, json)
            .apply()
        return true
    }

    override fun getRemoteConfiguration(): DataConfigurationModel? {
        val gson = Gson()
        val json = context.getSharedPreferences(REMOTE_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
            .getString(REMOTE_CONFIG_KEY, null)
        return gson.fromJson(json, DataConfigurationModel::class.java)
    }

    override fun storeAppConfiguration(config: AppConfig): Boolean {
        context.getSharedPreferences(APP_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
            .edit()
            .putString(APP_CONFIG_VIEWTYPE_KEY, config.viewType.name)
            .apply()
        return true
    }

    override fun getAppConfiguration(): AppConfig {
        val sp = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        val downloadImage = sp.getBoolean(context.getString(R.string.download_images_key), true)
        val cacheImage = sp.getBoolean(context.getString(R.string.cache_images_key), true)
        val themeStyle = sp.getBoolean(context.getString(R.string.theme_style_key), false)
        val notificationStatus = sp.getBoolean(context.getString(R.string.notification_key), false)

        val customPreferences =
            context.getSharedPreferences(APP_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
        val viewType: AppConfig.ViewType =
            AppConfig.ViewType.valueOf(
                customPreferences.getString(
                    APP_CONFIG_VIEWTYPE_KEY,
                    AppConfig.ViewType.LISTVIEW.name
                )!!
            )
        return AppConfig(
            downloadImage,
            cacheImage,
            themeStyle,
            notificationStatus,
            viewType
        )
    }

    override fun getFavoriteDao(): FavoritesDao {
        return movieDatabase.favoritesDao()
    }

    override fun getWatchlistDao(): WatchlistDao {
        return movieDatabase.watchlistDao()
    }

}

