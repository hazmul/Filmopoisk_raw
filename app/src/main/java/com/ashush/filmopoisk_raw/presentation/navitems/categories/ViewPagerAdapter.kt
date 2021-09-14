package com.ashush.filmopoisk_raw.presentation.navitems.categories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ashush.filmopoisk_raw.R
import com.ashush.filmopoisk_raw.presentation.navitems.categories.nowPlaying.NowPlayingFragment
import com.ashush.filmopoisk_raw.presentation.navitems.categories.topRated.TopRatedFragment
import com.ashush.filmopoisk_raw.presentation.navitems.categories.upcoming.UpcomingFragment


class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val fragments: Array<Fragment> = arrayOf(
        //Initialize fragments views
        //Fragment views are initialized like any other fragment (Extending Fragment)
        UpcomingFragment(),  //First fragment to be displayed within the pager tab number 1
        NowPlayingFragment(),
        TopRatedFragment(),
    )
    val fragmentNames = arrayOf( //Tabs names array
        fa.applicationContext.resources.getString(R.string.menu_upcoming),
        fa.applicationContext.resources.getString(R.string.menu_now_playing),
        fa.applicationContext.resources.getString(R.string.menu_top_rated),
    )

    override fun getItemCount(): Int {
        return fragments.size //Number of fragments displayed
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}