package com.amir.mart.paginglibraryv3.presentation.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.toLiveData
import com.amir.mart.paginglibraryv3.base.BaseViewModel
import com.amir.mart.paginglibraryv3.data.models.BaseResponse
import com.amir.mart.paginglibraryv3.data.source.db.Dao.MartDao
import com.amir.mart.paginglibraryv3.domain.usecase.MartContentUseCase
import com.amir.mart.paginglibraryv3.domain.usecase.MartLocleContentUseCase
import com.amir.mart.paginglibraryv3.domain.usecase.base.Outcome
import com.amir.mart.paginglibraryv3.presentation.Mart
import com.amir.mart.paginglibraryv3.presentation.MartContent


/**
 * Created by Amir.jehangir on 26,September,2021
 */
class MainViewModel(
    private var martContentUseCase: MartContentUseCase,
    private var martLocleContentUseCase: MartLocleContentUseCase,
    private var dao: MartDao
) : BaseViewModel() {

    val allMartData = dao.findAllMartData()
        .toLiveData(Config(pageSize = 60, enablePlaceholders = true, maxSize = 200))


    private lateinit var findMartDataSet: MutableLiveData<Outcome<BaseResponse<MartContent>>>
    private var resultfindMartLiveDataSet: MediatorLiveData<BaseResponse<MartContent>> = MediatorLiveData()
    //FindStore
    fun getMartLiveData() = resultfindMartLiveDataSet
    fun fetchMartData(findStoreRequest: String) {
        if (this::findMartDataSet.isInitialized)
            resultfindMartLiveDataSet.removeSource(findMartDataSet)
        findMartDataSet = martContentUseCase.start(
            true,
            viewModelScope,
            findStoreRequest
        )
        resultfindMartLiveDataSet.addSource(findMartDataSet) { outcome ->
            outcomeLiveData.value = outcome
            when (outcome) {
                is Outcome.Success -> {
                    outcome.data?.let {
                        resultfindMartLiveDataSet.value = it
                    }
                }
            }
        }
    }


    private lateinit var findLocalAgentStoreDataSet: MutableLiveData<Outcome<List<Mart>>>
    private var resultfindLocalAgentStoreLiveDataSetDb: MediatorLiveData<List<Mart>> = MediatorLiveData()
    //FindLocal
    fun fetchFindLocalStoreData(findStoreRequest: String) {
        if (this::findLocalAgentStoreDataSet.isInitialized)
            resultfindLocalAgentStoreLiveDataSetDb.removeSource(findLocalAgentStoreDataSet)
        findLocalAgentStoreDataSet = martLocleContentUseCase.start(
            true,
            viewModelScope,
            findStoreRequest
        )
        resultfindLocalAgentStoreLiveDataSetDb.addSource(findLocalAgentStoreDataSet) { outcome ->
            outcomeLiveData.value = outcome
            when (outcome) {
                is Outcome.Success -> {
                    outcome.data?.let {

                        var abc = it

//                        agentLiveData = it
                        resultfindLocalAgentStoreLiveDataSetDb.value = it
                    }
                }
            }
        }
    }
    fun getAgentDataFromDB() = resultfindLocalAgentStoreLiveDataSetDb
}