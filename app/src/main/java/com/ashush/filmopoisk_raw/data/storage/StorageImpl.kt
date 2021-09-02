package com.ashush.filmopoisk_raw.data.storage

import android.content.Context
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.google.gson.Gson


class StorageImpl(private val context: Context) : IStorage {

    companion object {
        const val REMOTE_CONFIG_PREFS_KEY = "REMOTE_CONFIG_PREFS_KEY"
        const val CONFIG_KEY = "CONFIG_KEY"
    }

    override fun storeRemoteConfiguration(config: DataConfigurationModel) {
        val gson = Gson()
        val json = gson.toJson(config)
        context.getSharedPreferences(REMOTE_CONFIG_PREFS_KEY, Context.MODE_PRIVATE).edit()
            .putString(CONFIG_KEY, json)
            .apply()
    }

    override fun getRemoteConfiguration(): DataConfigurationModel? {
        val gson = Gson()
        val json = context.getSharedPreferences(REMOTE_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
            .getString(CONFIG_KEY, null)
        return gson.fromJson(json, DataConfigurationModel::class.java)
    }
}