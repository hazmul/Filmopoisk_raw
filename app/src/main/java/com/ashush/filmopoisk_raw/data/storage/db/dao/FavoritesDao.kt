package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.ashush.filmopoisk_raw.data.storage.db.entity.Favorites

@Dao
abstract class FavoritesDao : BaseDao<Favorites>("favorites")
