package com.ashush.filmopoisk_raw.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ashush.filmopoisk_raw.MyApp
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.databinding.ActivityMainBinding
import com.ashush.filmopoisk_raw.di.presentation.injectViewModel
import com.ashush.filmopoisk_raw.domain.models.AppConfig
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

/**
 * Главный экран приложения. Содержит в себе логику навигации между фрагментами и формирует данные для бокового меню.**[initNav]**.
 * Отслеживает необходимость отображения кнопок на тулбаре **[bindObservers]**.
 *
 */

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var sharedViewModel: MainActivityViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Filmopoisk_Raw_NoActionBar)
        super.onCreate(savedInstanceState)

        (this.application as MyApp).application.inject(this)
        sharedViewModel = injectViewModel(this.viewModelFactory)

        initUI()
        initNav()
        bindObservers()
    }

    private fun initUI() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbarMain)
    }

    private fun initNav() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navMainPager,
                R.id.navSearch,
                R.id.navFavorites,
                R.id.navWatchlist,
                R.id.navSettings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun bindObservers() {
        sharedViewModel.optionMenuIsNeeded.observe(this) { result ->
            invalidateOptionsMenu()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.toolbar_menu_recyclerLayoutButton)?.isVisible =
            sharedViewModel.optionMenuIsNeeded.value ?: true

        when (sharedViewModel.viewTypeStatus.value) {
            AppConfig.ViewType.LISTVIEW -> {
                menu?.findItem(R.id.toolbar_menu_recyclerLayoutButton)?.icon =
                    ResourcesCompat.getDrawable(resources, R.drawable.outline_view_list_24, theme)
            }
            AppConfig.ViewType.GRIDVIEW -> {
                menu?.findItem(R.id.toolbar_menu_recyclerLayoutButton)?.icon =
                    ResourcesCompat.getDrawable(resources, R.drawable.outline_grid_view_24, theme)
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_recyclerLayoutButton -> {
                sharedViewModel.changeViewType()
                invalidateOptionsMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}