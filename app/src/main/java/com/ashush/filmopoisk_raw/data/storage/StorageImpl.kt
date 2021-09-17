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

/**
 * Класс реализация интерфеса [IStorage]
 * @param context для обращения к SharedPreferences приложения
 * @param movieDatabase для обращения к БД
 */
class StorageImpl @Inject constructor(
    private val context: Context,
    private val movieDatabase: MovieDatabase,
) : IStorage {

    companion object {
        private const val REMOTE_CONFIG_PREFS_KEY = "REMOTE_CONFIG_PREFS_KEY"
        private const val REMOTE_CONFIG_KEY = "REMOTE_CONFIG_KEY"
        private const val APP_CONFIG_PREFS_KEY = "APP_CONFIG_PREFS_KEY"
        private const val APP_CONFIG_VIEWTYPE_KEY = "APP_CONFIG_VIEWTYPE_KEY"
    }

    /**
     * Настройки API сохраняются в SharedPreferences в виде String как ответ от сервера
     */
    override fun storeRemoteConfiguration(config: DataConfigurationModel): Boolean {
        val gson = Gson()
        val json = gson.toJson(config)
        context.getSharedPreferences(REMOTE_CONFIG_PREFS_KEY, Context.MODE_PRIVATE).edit()
            .putString(REMOTE_CONFIG_KEY, json)
            .apply()
        return true
    }

    /**
     * Настройки API достаются из SharedPreferences как String и преобразовываются в DataConfigurationModel
     */
    override fun getRemoteConfiguration(): DataConfigurationModel? {
        val gson = Gson()
        val json = context.getSharedPreferences(REMOTE_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
            .getString(REMOTE_CONFIG_KEY, null)
        return gson.fromJson(json, DataConfigurationModel::class.java)
    }

    /**
     * Часть настроек приложения из [AppConfig] не нуждаются в сохранении т.к. они автоматически сохраняются в DefaultSharedPreferences
     */
    override fun storeAppConfiguration(config: AppConfig): Boolean {
        context.getSharedPreferences(APP_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
            .edit()
            .putString(APP_CONFIG_VIEWTYPE_KEY, config.viewType.name)
            .apply()
        return true
    }

    /**
     * Часть настроек приложения достаются из DefaultSharedPreferences, другая часть из определенных SharedPreferences
     * по итогу формируется один файл [AppConfig]
     */
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

