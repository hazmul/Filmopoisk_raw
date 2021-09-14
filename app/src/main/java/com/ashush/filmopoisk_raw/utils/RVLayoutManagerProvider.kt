package com.ashush.filmopoisk_raw.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashush.filmopoisk_raw.domain.models.DomainConfig

internal class RVLayoutManager {

    companion object {
        fun getLayout(context: Context, viewType: DomainConfig.ViewType?): RecyclerView.LayoutManager {
            return when (viewType) {
                DomainConfig.ViewType.LISTVIEW -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                DomainConfig.ViewType.GRIDVIEW -> GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                else -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

        }
    }

}