package com.etisalat.merchant.paginglibraryv3.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.etisalat.merchant.paginglibraryv3.R
import com.etisalat.merchant.paginglibraryv3.base.BaseFragment
import com.etisalat.merchant.paginglibraryv3.base.BaseViewModel
import com.etisalat.merchant.paginglibraryv3.base.MyDataBindingComponent
import com.etisalat.merchant.paginglibraryv3.databinding.FragmentMainBinding

import org.koin.android.viewmodel.ext.android.viewModel



/**
 * Created by Amir.jehangir on 24,September,2021
 */
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private val dataBindingComponent = MyDataBindingComponent(this)
    private val mainViewModel: MainViewModel by viewModel()
    lateinit var martListAdapter: MartListAdapter
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
                        populateMartListFromDb()
                    }
                }
            }
        })

        mainViewModel.fetchMartData("")

        return binding.root
    }


    override fun initUI() {



//        populateMartListFromDb()
    }


    private fun populateMartListFromDb() {
        martListAdapter = MartListAdapter { agentData ->

        }
        mainViewModel.allMartData.observe(this, Observer { loadedAgentData ->
            if (loadedAgentData.size > 0) {
                martListAdapter.submitList(loadedAgentData)
                binding.rvAgentList.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = martListAdapter

                }
            } else {
                Toast.makeText(activity, "no data", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun getViewModel(): BaseViewModel? {
        return mainViewModel

    }


}