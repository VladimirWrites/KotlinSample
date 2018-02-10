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

import android.view.ViewGroup

import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.domain.models.ItemPhoto
import com.vlad1m1r.kotlintest.presentation.base.BaseAdapter
import com.vlad1m1r.kotlintest.presentation.utils.ImageLoaderImpl

class ListAdapter : BaseAdapter<ListViewHolder, ItemPhoto>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder? =
            when (viewType) {
                R.layout.item_photo -> getViewHolder(parent)
                else -> throw IllegalArgumentException("ListAdapter supports only R.layout.item_photo")
            }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.setPhoto(list[position])

    override fun getItemViewType(position: Int): Int = R.layout.item_photo

    fun getViewHolder(parent: ViewGroup) =
            ListViewHolder(inflate(R.layout.item_photo, parent, false), ImageLoaderImpl())
}
