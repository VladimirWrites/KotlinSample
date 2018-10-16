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
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.domain.models.PhotoData
import org.junit.Test
import java.util.*

class ListAdapterTest {

    private val viewGroup = mock<ViewGroup>()
    private val listAdapter = spy(ListAdapter())

    @Test(expected = IllegalArgumentException::class)
    fun onCreateViewHolderWhenWrongViewType() {
        listAdapter.onCreateViewHolder(viewGroup, R.layout.item_photo + 1)
    }

    @Test
    fun onCreateViewHolder() {
        val listViewHolder = mock<ListViewHolder>()
        doReturn(listViewHolder).whenever(listAdapter).getViewHolder(viewGroup)
        assertThat(listAdapter.onCreateViewHolder(viewGroup, R.layout.item_photo)).isEqualTo(listViewHolder)
    }

    @Test
    fun onBindViewHolder() {
        val listViewHolder = mock<ListViewHolder>()
        val itemPhoto = PhotoData("name", "url")
        val list = mock<ArrayList<PhotoData>>()
        doReturn(list).whenever(listAdapter).list
        doReturn(itemPhoto).whenever(list)[any()]

        val position = (1..1000).random()
        listAdapter.onBindViewHolder(listViewHolder, position)
        verify(listViewHolder).setPhoto(itemPhoto)
    }

    @Test
    fun getItemViewType() {
        for(i in 1..100) {
            assertThat(listAdapter.getItemViewType((1..1000).random())).isEqualTo(R.layout.item_photo)
        }
    }

    private fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) +  start
}