package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.Dao
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist

/**
 * Класс для обращения к таблице [Watchlist]
 */

@Dao
abstract class WatchlistDao : BaseDao<Watchlist>("watchlist")
