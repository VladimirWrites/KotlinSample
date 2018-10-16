/*
 * Copyright 2017 Vladimir Jovanovic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vlad1m1r.kotlintest.presentation.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<T : RecyclerView.ViewHolder, H> : RecyclerView.Adapter<T>() {

    private val listOfData: ArrayList<H> = ArrayList()

    override fun getItemCount(): Int = listOfData.size

    var list: List<H>
        get() = this.listOfData
        set(list) {
            this.listOfData.clear()
            this.listOfData.addAll(list)
            notifyDataSetChanged()
        }

    fun addList(list: List<H>) {
        val oldSize: Int = this.listOfData.size
        this.listOfData.addAll(list)
        notifyItemRangeChanged(oldSize, this.listOfData.size)
    }

    fun getLayoutInflater(context: Context): LayoutInflater =
            LayoutInflater.from(context)

    fun inflate(resId: Int, parent: ViewGroup, attachToRoot: Boolean): View =
            LayoutInflater.from(parent.context).inflate(resId, parent, attachToRoot)
}
