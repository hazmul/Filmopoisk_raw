package com.ashush.filmopoisk_raw.data.storage

import com.ashush.filmopoisk_raw.data.storage.db.entity.BaseEntity
import com.ashush.filmopoisk_raw.models.data.configuration.DataConfigurationModel

interface IStorage {

    fun storeRemoteConfiguration(config: DataConfigurationModel)
    fun getRemoteConfiguration(): DataConfigurationModel?

    fun <T: BaseEntity> getAll(): List<BaseEntity>?
    fun <T: BaseEntity> getAllByIds(moviesId: List<Int>): List<BaseEntity>?
    fun <T: BaseEntity> updateMovies(moviesList: List<BaseEntity>)
    fun <T: BaseEntity> getById(movieId: Int): BaseEntity?
    fun <T: BaseEntity> delete(movieList: BaseEntity)
    fun <T: BaseEntity> insertAll(moviesList: List<T>)
}