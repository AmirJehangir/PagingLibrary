package com.amir.mart.paginglibraryv3.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.amir.mart.paginglibraryv3.domain.usecase.base.Outcome

/**
 * Created by Amir.jehangir on 22,September,2021
 */
abstract class BaseFragment : Fragment() {

    private var baseViewModel: BaseViewModel? = null

    abstract fun initUI()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        baseViewModel = getViewModel()
        baseViewModel?.outcomeLiveData?.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Outcome.Success -> {
                    (activity as BaseActivity).showPageLoader(true)
                }
                is Outcome.Start -> {
                    (activity as BaseActivity).showPageLoader(true)
                }
                is Outcome.End -> {
                    (activity as BaseActivity).showPageLoader(false)
                }
                is Outcome.Failure -> {
                    (activity as BaseActivity).showPageLoader(false)

                }
                is Outcome.NetworkError -> {
                    (activity as BaseActivity).showPageLoader(false)


                }
            }

        })
    }
    abstract fun getViewModel(): BaseViewModel?
}