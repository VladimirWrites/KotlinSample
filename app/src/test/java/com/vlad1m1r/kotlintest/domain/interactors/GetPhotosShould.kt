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

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.vlad1m1r.kotlintest.data.providers.PhotosProvider
import com.vlad1m1r.kotlintest.testutils.ITEM_PHOTO_OBSERVABLE
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPhotosShould {

    private val photosProvider = mock<PhotosProvider> {
        on { getPhotos(any(), any()) }.doReturn(ITEM_PHOTO_OBSERVABLE)
    }
    private val getPhotos = GetPhotos(photosProvider::getPhotos)

    @Test
    fun getPhotos() {
        assertEquals(getPhotos.getPhotos(0, 20), ITEM_PHOTO_OBSERVABLE)
    }

    @Test
    fun callPhotosProviderWithDefaultLimit() {
        getPhotos.getPhotos(0)
        verify(photosProvider).getPhotos(0, LIMIT)
    }
}