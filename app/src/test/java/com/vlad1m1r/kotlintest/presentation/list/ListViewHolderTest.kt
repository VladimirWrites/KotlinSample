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

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.domain.models.ItemPhoto
import com.vlad1m1r.kotlintest.presentation.utils.ImageLoader
import org.junit.Before
import org.junit.Test

class ListViewHolderTest {

    private val context = mock<Context>()
    private val imageView = mock<ImageView>()
    private val textView = mock<TextView>()

    private val itemView = mock<View>()
    private val imageLoader = mock<ImageLoader>()

    private val listViewHolder = ListViewHolder(itemView, imageLoader)

    private val itemPhoto = ItemPhoto("name", "url")

    @Before
    fun setUp() {
        whenever(itemView.context).thenReturn(context)
        whenever(itemView.findViewById<TextView>(R.id.textName)).thenReturn(textView)
        whenever(itemView.findViewById<ImageView>(R.id.imageView)).thenReturn(imageView)
    }

    @Test
    fun setPhoto() {
        listViewHolder.setPhoto(itemPhoto)
        verify(textView).text = itemPhoto.name
        verify(imageLoader).load(context, itemPhoto.url, imageView)
    }
}