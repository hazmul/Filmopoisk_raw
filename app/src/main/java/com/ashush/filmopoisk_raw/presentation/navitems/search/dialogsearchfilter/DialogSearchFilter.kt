package com.ashush.filmopoisk_raw.presentation.navitems.search.dialogsearchfilter

import android.content.Context
import android.view.LayoutInflater
import com.ashush.filmopoisk_raw.data.config.DataConfig
import com.ashush.filmopoisk_raw.databinding.DialogSearchFilterBinding
import com.ashush.filmopoisk_raw.presentation.navitems.search.SearchFilter
import com.ashush.filmopoisk_raw.presentation.navitems.search.dialogsearchfilter.adapter.GenreAdapter
import com.ashush.filmopoisk_raw.presentation.navitems.search.dialogsearchfilter.adapter.GenreItem
import com.ashush.filmopoisk_raw.presentation.navitems.search.dialogsearchfilter.adapter.GenreItemOnClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

/**
 * Функцию показывающая диалоговое окно с критериями фильтрации и
 * вызывающая функцию [applyFilter] с передачей в качестве параметра сформированного объекта [SearchFilter]
 */

fun Context.showSearchFilterDialog(
    defaultFilter: SearchFilter,
    applyFilter: (SearchFilter) -> Unit
) {
    val binding = DialogSearchFilterBinding.inflate(LayoutInflater.from(this))
    val dialog = BottomSheetDialog(this).apply { setContentView(binding.root) }

    var genres = DataConfig.genresInfo?.genres
        ?.map { GenreItem(it, it?.id in defaultFilter.genres) }
        ?: emptyList()

    binding.apply {
        val clickListener = GenreItemOnClickListener { genreId, newState ->
            genres = genres.map { if (it.genre?.id == genreId) it.copy(isChecked = newState) else it }
            (dialogSearchFilterGenres.adapter as GenreAdapter).setData(genres)
        }

        val adapter = GenreAdapter(genres, clickListener)
        dialogSearchFilterGenres.adapter = adapter

        setDefaultFilter(defaultFilter)

        dialogSearchFilterCancel.setOnClickListener { dialog.dismiss() }
        dialogSearchFilterApply.setOnClickListener { applyAction(applyFilter, dialog, genres) }
    }
    dialog.show()
}

/**
 * Установить значения view в диалоге на основании переданного фильтра
 */

private fun DialogSearchFilterBinding.setDefaultFilter(defaultFilter: SearchFilter) {
    dialogSearchFilter18Over.isChecked = defaultFilter.isAdult ?: false
    dialogSearchFilterDatesFrom.setText(defaultFilter.dateFrom.toString())
    dialogSearchFilterDatesTo.setText(defaultFilter.dateTo.toString())
    dialogSearchFilterVoteAverage.values =
        listOf(defaultFilter.minVoteAverage.toFloat(), defaultFilter.maxVoteAverage.toFloat())
}

/**
 * Функция формирующая объект [SearchFilter] для дальнейшего использования на основании переданных параметров.
 * Данные формируются на основании переданного списка [genres] и значений установленных в view.
 */

private fun DialogSearchFilterBinding.applyAction(
    applyFilter: (SearchFilter) -> Unit,
    dialog: BottomSheetDialog,
    genres: List<GenreItem>
) {
    applyFilter(
        SearchFilter(
            genres = genres.filter { it.isChecked }.map { it.genre?.id ?: 0 },
            isAdult = dialogSearchFilter18Over.isChecked,
            dateFrom = dialogSearchFilterDatesFrom.text.toString().toIntOrNull() ?: 1,
            dateTo = dialogSearchFilterDatesTo.text.toString().toIntOrNull() ?: Calendar.getInstance()
                .get(Calendar.YEAR),
            minVoteAverage = dialogSearchFilterVoteAverage.values[0].toDouble(),
            maxVoteAverage = dialogSearchFilterVoteAverage.values[1].toDouble()
        )
    )
    dialog.dismiss()
}