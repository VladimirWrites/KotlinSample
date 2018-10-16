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

package com.vlad1m1r.kotlintest.domain.interactors

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.vlad1m1r.kotlintest.data.models.Photo
import com.vlad1m1r.kotlintest.domain.PhotoState
import com.vlad1m1r.kotlintest.testutils.ITEM_PHOTO_LIST
import com.vlad1m1r.kotlintest.testutils.PHOTO_DATA_LIST
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class GetPhotosShould {

    @Test
    fun getData_whenResponseIsSuccessful() {
        val getPhotos = successfullyGetPhotos(PHOTO_DATA_LIST)

        val actual = runBlocking {
            getPhotos.getPhotos(0, 20)
        } as PhotoState.Data
        val expected  = PhotoState.Data(ITEM_PHOTO_LIST)

       assertThat(actual.data).isEqualTo(expected.data)
    }

    @Test
    fun getError_whenResponseThrowsException() {
        val exception = Exception("test")
        val getPhotos = errorGetPhotos(exception)

        val actual = runBlocking {
            getPhotos.getPhotos(0, 20)
        } as PhotoState.Error
        val expected  = PhotoState.Error(exception)

        assertThat(actual.exception).isEqualTo(expected.exception)
    }

    @Test
    fun callPhotosProviderWithDefaultLimit() {
        val photosProvider = mock<IPhotosProvider>()
        val getPhotos = GetPhotos(photosProvider)

        runBlocking {
            getPhotos.getPhotos(0)
        }
        verify(photosProvider).getPhotos(0, LIMIT)
    }

    private fun successfullyGetPhotos(photos: List<Photo>): GetPhotos {
        val photoData = GlobalScope.async {photos }
        val photosProvider = mock<IPhotosProvider> {
            on { getPhotos(any(), any()) }.doReturn(photoData)
        }
        return GetPhotos(photosProvider)
    }

    private fun errorGetPhotos(exception: Exception): GetPhotos {
        val photoData = GlobalScope.async {throw exception }
        val photosProvider = mock<IPhotosProvider> {
            on { getPhotos(any(), any()) }.doReturn(photoData)
        }
        return GetPhotos(photosProvider)
    }
}