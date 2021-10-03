package com.etisalat.merchant.paginglibraryv3.presentation.main

import android.R
import android.content.Context
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.toLiveData
import com.etisalat.merchant.paginglibraryv3.base.BaseViewModel
import com.etisalat.merchant.paginglibraryv3.data.models.BaseResponse
import com.etisalat.merchant.paginglibraryv3.data.source.db.Dao.MartDao
import com.etisalat.merchant.paginglibraryv3.domain.usecase.MartContentUseCase
import com.etisalat.merchant.paginglibraryv3.domain.usecase.base.Outcome
import com.etisalat.merchant.paginglibraryv3.presentation.Mart
import com.etisalat.merchant.paginglibraryv3.presentation.MartContent
import com.etisalat.merchant.paginglibraryv3.util.utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream


/**
 * Created by Amir.jehangir on 26,September,2021
 */
class MainViewModel(
    private var martContentUseCase: MartContentUseCase,
    private val dao: MartDao
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
}