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

package com.vlad1m1r.kotlintest.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup

import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.data.models.ItemPhoto
import com.vlad1m1r.kotlintest.presentation.base.BaseAdapter

class ListAdapter : BaseAdapter<ListViewHolder, ItemPhoto>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder? {
        when (viewType) {
            R.layout.item_photo -> return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))
            else -> return null
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.setPhoto(listOfData[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_photo
    }
}
