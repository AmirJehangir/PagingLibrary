package com.amir.mart.paginglibraryv3.presentation.main

import android.os.Bundle
import android.util.Log
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
import com.amir.mart.paginglibraryv3.databinding.FragmentMapMainBinding
import com.amir.mart.paginglibraryv3.presentation.Mart
import com.amir.mart.paginglibraryv3.presentation.main.mapUtils.MarkerClusterItem
import com.amir.mart.paginglibraryv3.presentation.main.mapUtils.OwnIconRendered
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Amir.jehangir on 07,October,2021
 */
class MainMapFragment  : BaseFragment(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    private lateinit var binding: FragmentMapMainBinding
    private val dataBindingComponent = MyDataBindingComponent(this)
    private val mainViewModel: MainViewModel by viewModel()
    private var googleMap: GoogleMap? = null
    private lateinit var clusterManager: ClusterManager<MarkerClusterItem>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_map_main, container,
            false,
            dataBindingComponent
        )

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }


    override fun initUI() {

        mainViewModel.getAgentDataFromDB().observe(this, Observer { resultList ->
            resultList?.let {
                var result = it
                result.let {
                    if (result.size > 0) {
                        updateGoogleMapOnDataChange(result as ArrayList<Mart>)
                    } else {

                    }
                }
            }
        })
        mainViewModel.fetchFindLocalStoreData("sampleQuery")
    }


    override fun getViewModel(): BaseViewModel? {
        return mainViewModel

    }

    override fun onMapReady(maps: GoogleMap?) {
        googleMap = maps
        googleMap!!.clear()


    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        return true
    }


    private fun updateGoogleMapOnDataChange(agentContentList: ArrayList<Mart>) {
        googleMap!!.clear()
        clusterManager = ClusterManager(context, googleMap)
        googleMap!!.setInfoWindowAdapter(clusterManager.getMarkerManager())
//        clusterManager.setOnClusterItemInfoWindowClickListener(this)

        googleMap!!.setOnCameraIdleListener(clusterManager)

        googleMap!!.setOnMarkerClickListener(clusterManager)
        googleMap!!.setOnInfoWindowClickListener(clusterManager)

        clusterManager.setRenderer(OwnIconRendered(activity, googleMap!!, clusterManager))


        GlobalScope.launch(Dispatchers.Main) {
            updateGoogleMapData(agentContentList)
//            moveGoogleMapCamera(LatLng(agentContentList.first().latitude!!, agentContentList.first().longitude!!),true)
        }

    }

    private fun updateGoogleMapData(martList: ArrayList<Mart>) {
        googleMap.let {
            for (itemAgent in martList) {
                if (itemAgent.latitude != null && itemAgent.longitude != null) {
                    createMarker(itemAgent)
                }
            }

        }
    }

    protected fun createMarker(agentContent: Mart) {
        return clusterManager.addItem(
            MarkerClusterItem(
                agentContent.latitude!!,
                agentContent.longitude!!,
                agentContent.agentName!!,
                agentContent.agentName!!,
                agentContent.brandIcon!!
            )
        )
    }

}