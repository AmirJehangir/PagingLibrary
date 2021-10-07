package com.amir.mart.paginglibraryv3.presentation.main

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.amir.mart.paginglibraryv3.R
import com.amir.mart.paginglibraryv3.presentation.Mart

/**
 * Created by Amir.jehangir on 29,September,2021
 */
class MartViewHolder (parent: ViewGroup, private val clickListener: (mart: Mart) -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
) {

    var mContext : Context
    init {
        mContext = parent.context
    }

    private val tv_martName = itemView.findViewById<AppCompatTextView>(R.id.tv_agentName)
    private val tv_martlocation = itemView.findViewById<AppCompatTextView>(R.id.tv_agentlocation)
    private val tv_marttype = itemView.findViewById<AppCompatTextView>(R.id.tv_agenttype)
    private val img_martName = itemView.findViewById<AppCompatImageView>(R.id.img_Agent)
    private val tv_martIKms = itemView.findViewById<AppCompatTextView>(R.id.tv_agentIKms)
    private val tv_martOpenClose = itemView.findViewById<AppCompatTextView>(R.id.tv_agentOpenClose)
    private val cl_main = itemView.findViewById<ConstraintLayout>(R.id.cl_main)
    var mart: Mart? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(agentData: Mart?) {
        if(mContext == null){
            return
        }
        this.mart = agentData
        tv_martName.text = agentData?.agentName
        cl_main.setOnClickListener {
            clickListener(agentData!!)
        }
        tv_martlocation.text = agentData?.distance?.toString()
        tv_marttype.text = agentData?.agentName
        if(agentData?.distance != null){
            var distanceFormat = String.format("%.1f",agentData?.distance)
            tv_martIKms.text = distanceFormat  + " Kms"
        }else{
            tv_martIKms.text = ""
        }

        tv_martOpenClose.text =   agentData?.status
        Glide.with(img_martName.context)
            .asBitmap()
            .load(R.drawable.ic_cashincashoutagents)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    img_martName.setImageBitmap(resource)
                    img_martName.visibility = View.VISIBLE
                }

            })
    }
}