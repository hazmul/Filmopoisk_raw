package com.ashush.filmopoisk_raw.di.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.presentation.nav_items.launch.LaunchViewModel
import com.ashush.filmopoisk_raw.presentation.nav_items.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    internal abstract fun launchViewModel(viewModel: LaunchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun detailActivityViewModel(viewModel: SearchViewModel): ViewModel
}