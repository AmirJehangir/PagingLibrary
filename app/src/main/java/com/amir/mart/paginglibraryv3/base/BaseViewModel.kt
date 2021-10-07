package com.amir.mart.paginglibraryv3.base

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.mart.paginglibraryv3.domain.usecase.base.Outcome


import kotlinx.coroutines.cancel
/**
 * Created by Amir.jehangir on 24,September,2021
 */
abstract class BaseViewModel : ViewModel() {

    var outcomeLiveData = MediatorLiveData<Outcome<*>>()

    // Cancel the job when the view model is destroyed
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}