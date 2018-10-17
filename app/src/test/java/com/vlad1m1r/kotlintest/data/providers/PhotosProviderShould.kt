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

package com.vlad1m1r.kotlintest.data.providers

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vlad1m1r.kotlintest.data.ApiInterface
import com.vlad1m1r.kotlintest.data.models.Photo
import kotlinx.coroutines.experimental.Deferred
import org.junit.Test

class PhotosProviderShould {

    private val apiInterface = mock<ApiInterface>()
    private val photosProvider = PhotosProvider(apiInterface)
    private val data = mock<Deferred<List<Photo>>>()

    @Test
    fun callApiInterface_withSameParams() {
        photosProvider.getPhotos(0, 21)
        verify(apiInterface).getPhotos(0, 21)
    }

    @Test
    fun getPassDataFromApiInterface() {
        whenever(apiInterface.getPhotos(any(), any())).thenReturn(data)
        assertThat(photosProvider.getPhotos(0, 21)).isEqualTo(data)
    }
}