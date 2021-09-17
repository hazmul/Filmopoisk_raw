package com.ashush.filmopoisk_raw.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.domain.models.AppConfig

/**
 * Функция помогающая определить тип RecyclerView.LayoutManager в зависимости от [AppConfig.ViewType]
 */

fun getLayout(context: Context, viewType: AppConfig.ViewType?): RecyclerView.LayoutManager {
    return when (viewType) {
        AppConfig.ViewType.LISTVIEW -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        AppConfig.ViewType.GRIDVIEW -> GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        else -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}


