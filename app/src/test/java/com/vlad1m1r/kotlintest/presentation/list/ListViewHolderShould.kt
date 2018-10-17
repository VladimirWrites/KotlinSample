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
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.View
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.vlad1m1r.kotlintest.domain.models.PhotoData
import com.vlad1m1r.kotlintest.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.item_photo.view.*
import org.junit.Test

class ListViewHolderShould {

    private val context = mock<Context>()
    private val imageView = mock<AppCompatImageView>()
    private val textView = mock<AppCompatTextView>()

    private val itemView = mock<View> {
        on { context } doReturn context
        on { textName } doReturn textView
        on { imageView } doReturn imageView
    }
    private val imageLoader = mock<ImageLoader>()
    private val listViewHolder = ListViewHolder(itemView, imageLoader)
    private val photoData = PhotoData("name", "url")

    @Test
    fun showData_whenPhotoDataPassed() {
        listViewHolder.setPhoto(photoData)
        verify(textView).text = photoData.name
        verify(imageLoader).load(context, photoData.url, imageView)
    }
}