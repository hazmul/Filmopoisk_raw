package com.ashush.filmopoisk_raw.utils

import android.content.Context
import android.util.SparseIntArray
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashush.filmopoisk_raw.MyApp
import com.ashush.filmopoisk_raw.domain.models.AppConfig
import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RVLayoutManagerTest {

    private val context: Context = mockk<MyApp>(relaxed = true, relaxUnitFun = true)
    private val listview = AppConfig.ViewType.LISTVIEW
    private val gridview = AppConfig.ViewType.GRIDVIEW

    @Before
    fun init() {
        mockkConstructor(GridLayoutManager::class)
        mockkConstructor(LinearLayoutManager::class)
        mockkConstructor(SparseIntArray::class)
    }

    @Test
    fun getLayoutCaseListView() {

        assertEquals(
            LinearLayoutManager::class.java.name,
            getLayout(context, listview)::class.java.name
        )
    }

    @Test
    fun getLayoutCaseGridView() {

        every { anyConstructed<SparseIntArray>().clear() } just Runs

        assertEquals(
            GridLayoutManager::class.java.name,
            getLayout(context, gridview)::class.java.name
        )
    }

    @Test

    fun getLayoutCaseNull() {

        assertEquals(
            LinearLayoutManager::class.java.name,
            getLayout(context, null)::class.java.name
        )
    }
}
