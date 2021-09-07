package com.ashush.filmopoisk_raw.data.storage

import android.content.Context
import com.ashush.filmopoisk_raw.data.storage.db.DBRoom
import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel
import com.google.gson.Gson

class StorageImpl(
    private val context: Context,
    private var dbRoom: DBRoom,
) : IStorage {

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

    override fun <T: BaseEntity> getAll(): List<BaseEntity>? {
        val dao = dbRoom.baseDao<T>()
        return dao.getAll()
    }

    override fun <T: BaseEntity> getAllByIds(moviesId: List<Int>): List<BaseEntity>? {
        val dao = dbRoom.baseDao<T>()
        return dao.getAllByIds(moviesId)
    }

    override fun <T: BaseEntity> getById(movieId: Int): BaseEntity? {
        val dao = dbRoom.baseDao<T>()
        return dao.getById(movieId)
    }

    override fun <T: BaseEntity> delete(movieList: BaseEntity) {
        val dao = dbRoom.baseDao<T>()
        dao.delete(movieList)
    }

    override fun <T: BaseEntity> updateMovies(moviesList: List<BaseEntity>) {
        val dao = dbRoom.baseDao<T>()
        dao.updateMovies(moviesList)
    }

    override fun <T: BaseEntity> insertAll(moviesList: List<T>) {
        val dao = dbRoom.baseDao<T>()
        dao.insertAll(moviesList)
    }
}