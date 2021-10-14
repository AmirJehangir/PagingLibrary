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
import com.amir.mart.paginglibraryv3.databinding.FragmentListingMainBinding
import com.amir.mart.paginglibraryv3.databinding.FragmentMainBinding
import com.amir.mart.paginglibraryv3.databinding.FragmentMartListBindingImpl
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Amir.jehangir on 07,October,2021
 */
class MainListingFragment  : BaseFragment() {

    private lateinit var binding: FragmentListingMainBinding
    private val dataBindingComponent = MyDataBindingComponent(this)
    private val mainViewModel: MainViewModel by viewModel()
    lateinit var martListAdapter: MartListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_listing_main, container,
            false,
            dataBindingComponent
        )

        populateMartListFromDb()

        return binding.root
    }


    override fun initUI() {

    }


    private fun populateMartListFromDb() {
        martListAdapter = MartListAdapter { martData ->

        }
        mainViewModel.allMartData.observe(this, Observer { loadedAgentData ->
                martListAdapter.submitList(loadedAgentData)
                binding.rvAgentList.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = martListAdapter
                }
        })
    }



    override fun getViewModel(): BaseViewModel? {
        return mainViewModel

    }


}