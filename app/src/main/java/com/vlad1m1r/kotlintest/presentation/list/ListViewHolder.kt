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

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.data.models.ItemPhoto
import com.vlad1m1r.kotlintest.presentation.base.BaseViewHolder

class ListViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun setPhoto(itemPhoto: ItemPhoto) {
        itemView.findViewById<TextView>(R.id.textName).text = itemPhoto.name
        Glide.with(itemView.context).load(itemPhoto.url).into(itemView.findViewById<ImageView>(R.id.imageView))
    }
}
