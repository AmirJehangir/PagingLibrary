package com.amir.mart.paginglibraryv3.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by Amir.jehangir on 07,October,2021
 */
class TabsMainAdapter (fragmentManager: FragmentManager, Size : Int) :
    FragmentStatePagerAdapter(fragmentManager) {

    private var totalPageSize = Size

    // 2
    override fun getItem(position: Int): Fragment {
        if(position == 0){

            var agentListFragment = MainListingFragment()
//            agentListFragment.arguments = bundleData
            return agentListFragment
        }else if(position == 1){
            var agentMapFragment = MainMapFragment()
            return agentMapFragment
        }
        return MainListingFragment()
    }

    // 3
    override fun getCount(): Int {
        return totalPageSize
    }
}