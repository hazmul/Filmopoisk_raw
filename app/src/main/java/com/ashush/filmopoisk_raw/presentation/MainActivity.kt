package com.ashush.filmopoisk_raw.presentation

import android.content.Context
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
import com.ashush.filmopoisk_raw.domain.models.DomainConfig
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val DOMAIN_CONFIG_PREFS_KEY = "DOMAIN_CONFIG_PREFS_KEY"
        const val DOMAIN_CONFIG_VIEWTYPE_KEY = "DOMAIN_CONFIG_VIEWTYPE_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainActivityViewModel

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Filmopoisk_Raw_NoActionBar)
        super.onCreate(savedInstanceState)

        (this.application as MyApp).application.inject(this)

        viewModel = injectViewModel(this.viewModelFactory)
        viewModel.optionMenuIsNeeded.observe(this) { result ->
            invalidateOptionsMenu()
        }
        initUI()
        initNav()
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
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.toolbar_menu_recyclerLayoutButton)?.isVisible = viewModel.optionMenuIsNeeded.value ?: true

        val customPreferences = getSharedPreferences(DOMAIN_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
        when (customPreferences.getString(DOMAIN_CONFIG_VIEWTYPE_KEY, DomainConfig.ViewType.LISTVIEW.name)) {
            DomainConfig.ViewType.LISTVIEW.name -> {
                menu?.findItem(R.id.toolbar_menu_recyclerLayoutButton)?.icon =
                    ResourcesCompat.getDrawable(resources, R.drawable.outline_view_list_24, theme)
            }
            DomainConfig.ViewType.GRIDVIEW.name -> {
                menu?.findItem(R.id.toolbar_menu_recyclerLayoutButton)?.icon =
                    ResourcesCompat.getDrawable(resources, R.drawable.outline_grid_view_24, theme)
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_recyclerLayoutButton -> {
                val customPreferences = getSharedPreferences(DOMAIN_CONFIG_PREFS_KEY, Context.MODE_PRIVATE)
                val newViewType: String =
                    when (customPreferences.getString(
                        DOMAIN_CONFIG_VIEWTYPE_KEY,
                        DomainConfig.ViewType.LISTVIEW.name
                    )) {
                        DomainConfig.ViewType.LISTVIEW.name -> DomainConfig.ViewType.GRIDVIEW.name
                        DomainConfig.ViewType.GRIDVIEW.name -> DomainConfig.ViewType.LISTVIEW.name
                        else -> DomainConfig.ViewType.GRIDVIEW.name
                    }
                customPreferences
                    .edit()
                    .putString(DOMAIN_CONFIG_VIEWTYPE_KEY, newViewType)
                    .apply()
                viewModel.setAppSettings(recyclerViewType = DomainConfig.ViewType.valueOf(newViewType))
                invalidateOptionsMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}