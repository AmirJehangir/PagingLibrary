package com.etisalat.merchant.paginglibraryv3.base

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.etisalat.merchant.paginglibraryv3.R
import com.etisalat.merchant.paginglibraryv3.data.source.SharedPrefDataSource
import com.etisalat.merchant.paginglibraryv3.databinding.ActivityBaseBinding
import org.koin.android.ext.android.inject

/**
 * Created by Amir.jehangir on 22,September,2021
 */
abstract class BaseActivity : AppCompatActivity() {

    private lateinit var activityBaseBinding: ActivityBaseBinding
    val sharedPref: SharedPrefDataSource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)


    }

    override fun setContentView(layoutResID: Int) {
        if (this::activityBaseBinding.isInitialized) {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            val stubView = inflater.inflate(layoutResID, activityBaseBinding.viewStub, false)
            activityBaseBinding.viewStub.removeAllViews()
            activityBaseBinding.viewStub.addView(stubView, lp)
        } else {
            super.setContentView(layoutResID)
        }
    }

    override fun setContentView(view: View) {
        if (this::activityBaseBinding.isInitialized) {
            val lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            activityBaseBinding.viewStub.removeAllViews()
            activityBaseBinding.viewStub.addView(view, lp)
        }
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        if (this::activityBaseBinding.isInitialized) {
            activityBaseBinding.viewStub.removeAllViews()
            activityBaseBinding.viewStub.addView(view, params)
        }
    }

    open fun showPageLoader(show: Boolean) {
        if (show) {
            activityBaseBinding.flProgress.visibility = View.VISIBLE
        } else {
            activityBaseBinding.flProgress.visibility = View.GONE
            activityBaseBinding.viewStub.visibility = View.VISIBLE
        }
    }
}