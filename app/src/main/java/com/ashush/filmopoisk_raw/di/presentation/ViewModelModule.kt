package com.ashush.filmopoisk_raw.di.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ashush.filmopoisk_raw.presentation.DetailViewModel
import com.ashush.filmopoisk_raw.presentation.MainActivityViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.categories.nowPlaying.NowPlayingViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.categories.topRated.TopRatedViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.categories.upcoming.UpcomingViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.favorites.FavoritesViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.launch.LaunchViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.search.SearchViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.settings.SettingsViewModel
import com.ashush.filmopoisk_raw.presentation.navitems.watchlist.WatchlistViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun detailFragmentViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    internal abstract fun settingsFragmentViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun mainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    internal abstract fun launchViewModel(viewModel: LaunchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingViewModel::class)
    internal abstract fun upcomingViewModel(viewModel: UpcomingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedViewModel::class)
    internal abstract fun topRatedViewModel(viewModel: TopRatedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    internal abstract fun nowPlayingViewModel(viewModel: NowPlayingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    internal abstract fun favoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WatchlistViewModel::class)
    internal abstract fun watchlistViewModel(viewModel: WatchlistViewModel): ViewModel
}