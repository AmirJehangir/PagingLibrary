package com.amir.mart.paginglibraryv3.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.amir.mart.paginglibraryv3.R
import com.amir.mart.paginglibraryv3.base.BaseFragment
import com.amir.mart.paginglibraryv3.base.BaseViewModel
import com.amir.mart.paginglibraryv3.base.MyDataBindingComponent
import com.amir.mart.paginglibraryv3.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout

import org.koin.android.viewmodel.ext.android.viewModel



/**
 * Created by Amir.jehangir on 24,September,2021
 */
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private val dataBindingComponent = MyDataBindingComponent(this)
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var tabsMainAdapter: TabsMainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container,
            false,
            dataBindingComponent
        )

        mainViewModel.getMartLiveData().observe(this, Observer { resultList ->
            resultList?.let {
                var result = it
                result.let {
                    it.data.let { data ->
                        generateNotificationsTabs()
//                        populateMartListFromDb()
                    }
                }
            }
        })

        mainViewModel.fetchMartData("")

        return binding.root
    }


    override fun initUI() {
    }

    private fun generateNotificationsTabs() {


        binding.agentTablayout.addTab(
            binding.agentTablayout.newTab().setText("Listing")
        )
        binding.agentTablayout.addTab(
            binding.agentTablayout.newTab().setText("Map")
        )

        binding.agentTablayout.tabMode = TabLayout.MODE_FIXED
        tabsMainAdapter = TabsMainAdapter(childFragmentManager, 2)
        binding.agentViewPager.adapter = tabsMainAdapter


        binding.agentViewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.agentTablayout
            )
        )
        binding.agentTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.agentViewPager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun getViewModel(): BaseViewModel? {
        return mainViewModel

    }


}