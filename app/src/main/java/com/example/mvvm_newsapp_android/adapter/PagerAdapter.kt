package com.example.mvvm_newsapp_android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mvvm_newsapp_android.fragment.*

class PagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                HotFragment()
            }
            1 -> EntertainmentFragment()
            2 -> BusinesFragment()
            3 -> GeneralFragment()
            4 -> HealtFragment()
            5 -> ScienceFragment()
            6 -> SportFragment()
            else -> {
                return TechnologyFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 8
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "Explore"
            1 -> "Entertainment"
            2 -> "Business"
            3 -> "General"
            4 -> "Health"
            5 -> "Science"
            6 -> "Sports"
            else -> "Technology"
        }
    }
}