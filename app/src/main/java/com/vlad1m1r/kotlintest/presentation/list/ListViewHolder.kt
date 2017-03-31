package com.vlad1m1r.kotlintest.presentation.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.data.models.ItemPhoto
import com.vlad1m1r.kotlintest.presentation.base.BaseViewHolder


/**
 * Created by vladimirjovanovic on 2/28/17.
 */

class ListViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun setPhoto(itemPhoto: ItemPhoto) {
        (itemView.findViewById(R.id.textName) as TextView).text = itemPhoto.name
        Glide.with(itemView.context).load(itemPhoto.url).into(itemView.findViewById(R.id.imageView) as ImageView)
    }
}
