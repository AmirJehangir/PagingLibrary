package com.amir.mart.paginglibraryv3.presentation.main.mapUtils

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Created by Amir.jehangir on 07,October,2021
 */
class MarkerClusterItem (
    lat: Double,
    lng: Double,
    title: String,
    snippet: String,
    iconResource: String
) : ClusterItem {

    private val position: LatLng
    private val title: String
    private val snippet: String
    private var iconResource: String

    public fun getItemIcon(): String {
        return iconResource
    }

    override fun getPosition(): LatLng {
        return position
    }

    override fun getTitle(): String? {
        return title
    }

    override fun getSnippet(): String? {
        return snippet
    }

    init {
        position = LatLng(lat, lng)
        this.title = title
        this.snippet = snippet
        this.iconResource = iconResource
    }
}