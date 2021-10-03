package com.etisalat.merchant.paginglibraryv3

import android.app.Application
import android.content.Context
import android.util.Log
import com.etisalat.merchant.paginglibraryv3.util.AppKoin

/**
 * Created by Amir.jehangir on 26,September,2021
 */
class MainPaging : Application() {
    override fun onCreate() {
        super.onCreate()
        AppKoin().start(this@MainPaging)
    }

    init {
        instance = this
    }

    companion object {
        private var instance: MainPaging? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}