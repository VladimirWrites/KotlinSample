package com.vlad1m1r.kotlintest.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup

import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.data.models.ItemPhoto
import com.vlad1m1r.kotlintest.presentation.base.BaseAdapter

/**
 * Created by vladimirjovanovic on 3/23/17.
 */

class ListAdapter : BaseAdapter<ListViewHolder, ItemPhoto>() {

    val TYPE_LANGUAGE = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder? {
        when (viewType) {
            TYPE_LANGUAGE -> return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))
            else -> return null
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.setPhoto(listOfData[position])
    }
}
