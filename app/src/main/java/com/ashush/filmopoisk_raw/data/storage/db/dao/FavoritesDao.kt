package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.Dao
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites

/**
 * Класс для обращения к таблице [Favorites]
 */
@Dao
abstract class FavoritesDao : BaseDao<Favorites>("favorites")
