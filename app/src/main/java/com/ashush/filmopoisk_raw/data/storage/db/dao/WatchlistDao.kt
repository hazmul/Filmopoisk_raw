package com.ashush.filmopoisk_raw.data.storage.db.dao

import androidx.room.*
import com.ashush.filmopoisk_raw.data.storage.db.entity.Watchlist

@Dao
abstract class WatchlistDao : BaseDao<Watchlist>("watchlist")

