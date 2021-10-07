package com.amir.mart.paginglibraryv3.presentation.main.mapUtils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.amir.mart.paginglibraryv3.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 * Created by Amir.jehangir on 07,October,2021
 */
class OwnIconRendered (
    context: Context?,
    map: GoogleMap?,
    clusterManager: ClusterManager<MarkerClusterItem>?
) : DefaultClusterRenderer<MarkerClusterItem>(context, map, clusterManager) {
    private var mCustomMarkerView: View? = null
    private var mMarkerImageView: ImageView? = null
    var mContext = context
    init {
        mCustomMarkerView = (mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.view_custom_marker, null)
        mMarkerImageView = mCustomMarkerView!!.findViewById<View>(R.id.iv_store) as ImageView

    }


    override fun onBeforeClusterItemRendered(
        item: MarkerClusterItem,
        markerOptions: MarkerOptions
    ) {
        markerOptions.snippet(item.getSnippet())
        markerOptions.title(item.getTitle())
        super.onBeforeClusterItemRendered(item, markerOptions)
    }

    override fun onClusterItemRendered(clusterItem: MarkerClusterItem?, marker: Marker?) {
        super.onClusterItemRendered(clusterItem, marker)

        marker?.showInfoWindow()
        Glide.with(mContext!!)
            .asBitmap()
//            .load(clusterItem!!.getItemIcon())
            .load(R.drawable.ic_cashincashoutagents)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions().override(300, 300))
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    marker!!.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView!!, resource)))
                }

            })
    }


    private fun getMarkerBitmapFromView(view: View, bitmap: Bitmap): Bitmap? {
        mMarkerImageView!!.setImageBitmap(bitmap)
        view.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        val returnedBitmap = Bitmap.createBitmap(
            view.measuredWidth, view.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(returnedBitmap)
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)
        val drawable = view.background
        drawable?.draw(canvas)
        view.draw(canvas)
        return returnedBitmap
    }

}