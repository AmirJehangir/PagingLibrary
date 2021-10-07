package com.amir.mart.paginglibraryv3.presentation.main

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.amir.mart.paginglibraryv3.presentation.Mart

/**
 * Created by Amir.jehangir on 29,September,2021
 */
class MartListAdapter (private val clickListener: (mart: Mart) -> Unit) : PagedListAdapter<Mart, MartViewHolder>(diffCallback) {


    override fun onBindViewHolder(holder: MartViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MartViewHolder = MartViewHolder(parent,clickListener)

}


val diffCallback: DiffUtil.ItemCallback<Mart> = object : DiffUtil.ItemCallback<Mart>() {
    override fun areItemsTheSame(oldItem: Mart, newItem: Mart): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Mart, newItem: Mart): Boolean {
        return oldItem == newItem
    }
}