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

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.vlad1m1r.kotlintest.data.ApiInterface
import com.vlad1m1r.kotlintest.testutils.ITEM_PHOTO_LIST
import com.vlad1m1r.kotlintest.testutils.PHOTO_DATA_OBSERVABLE
import junit.framework.Assert.assertEquals
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotosProviderTest {

    val apiInterface = mock<ApiInterface>()
    val photosProvider = PhotosProvider(apiInterface)

    @Test
    fun getGetPhotos() {
        whenever(apiInterface.getPhotos(any(), any())).thenReturn(PHOTO_DATA_OBSERVABLE)
        val actual = photosProvider.getPhotos(0, 20).blockingFirst()
        assertEquals(actual, ITEM_PHOTO_LIST)
    }
}