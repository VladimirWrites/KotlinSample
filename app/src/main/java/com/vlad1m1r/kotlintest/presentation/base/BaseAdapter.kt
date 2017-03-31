package com.vlad1m1r.kotlintest.presentation.base

import android.support.v7.widget.RecyclerView

import kotlin.collections.ArrayList

/**
 * Created by vladimirjovanovic on 12/20/16.
 */

abstract class BaseAdapter<T : RecyclerView.ViewHolder, H> : RecyclerView.Adapter<T>() {

    protected val mList: ArrayList<H> = ArrayList<H>();

    override fun getItemCount(): Int {
        return mList.size
    }

    var list: ArrayList<H>
        get() = mList
        set(list) {
            this.mList.clear()
            this.mList.addAll(list)
            notifyDataSetChanged()
        }

    fun addList(list:ArrayList<H>) {
        val oldSize:Int = mList.size
        mList.addAll(list)
        notifyItemRangeChanged(oldSize, mList.size)
    }
}
