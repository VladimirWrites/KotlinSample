package com.vlad1m1r.kotlintest.presentation.base

import android.support.v7.widget.RecyclerView

import kotlin.collections.ArrayList

/**
 * Created by vladimirjovanovic on 12/20/16.
 */

abstract class BaseAdapter<T : RecyclerView.ViewHolder, H> : RecyclerView.Adapter<T>() {

    protected val listOfData: ArrayList<H> = ArrayList()

    override fun getItemCount(): Int {
        return listOfData.size
    }

    var list: ArrayList<H>
        get() = this.listOfData
        set(list) {
            this.listOfData.clear()
            this.listOfData.addAll(list)
            notifyDataSetChanged()
        }

    fun addList(list:ArrayList<H>) {
        val oldSize:Int = this.listOfData.size
        this.listOfData.addAll(list)
        notifyItemRangeChanged(oldSize, this.listOfData.size)
    }
}
