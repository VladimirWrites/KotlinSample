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

package com.vlad1m1r.kotlintest.data.interactors

import com.vlad1m1r.kotlintest.data.ApiInterface
import com.vlad1m1r.kotlintest.data.models.PhotoData
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPhotosTest {

    @Mock
    var apiInterface:ApiInterface? = null

    @Test
    fun formatData() {
        val getPhotos:GetPhotos = GetPhotos(apiInterface!!)

        val photosData:ArrayList<PhotoData> = arrayListOf()

        (0..3).mapTo(photosData) {
            PhotoData(
                    albumId = 100 + it,
                    id = it,
                    title = "test" + it,
                    url = "url" + it,
                    thumbnailUrl = "thumbnail" + it
            )
        }

        val photos = getPhotos.formatData(photosData)
        for(i:Int in 0..3) {
            val photo = photos[i]
            assertEquals(photo.name, "test"+i)
            assertEquals(photo.url, "url"+i)
        }

        val photosDataEmpty:ArrayList<PhotoData> = arrayListOf()

        (0..3).mapTo(photosDataEmpty) {
            PhotoData(
                    albumId = 100 + it,
                    id = it,
                    thumbnailUrl = "thumbnail" + it
            )
        }

        val photosEmpty = getPhotos.formatData(photosDataEmpty)
        for(i:Int in 0..3) {
            val photo = photosEmpty[i]
            assertEquals(photo.name, "")
            assertEquals(photo.url, "")
        }
    }
}