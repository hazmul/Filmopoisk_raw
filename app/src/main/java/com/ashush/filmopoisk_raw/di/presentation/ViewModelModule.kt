package com.ashush.filmopoisk_raw.di.presentation

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(MainActivityViewModel::class)
//    internal abstract fun mainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailActivityViewModel::class)
//    internal abstract fun detailActivityViewModel(viewModel: DetailActivityViewModel): ViewModel
}