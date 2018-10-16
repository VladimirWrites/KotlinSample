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

import com.vlad1m1r.kotlintest.domain.Data
import com.vlad1m1r.kotlintest.domain.PhotoState
import com.vlad1m1r.kotlintest.domain.models.PhotoData
import kotlinx.coroutines.experimental.Deferred
import java.lang.Exception

internal const val LIMIT = 20

interface IPhotosProvider {
    fun getPhotos(offset: Int, limit: Int): Deferred<List<Data<PhotoData>>>
}

class GetPhotos(private var photosProvider: IPhotosProvider) {
    suspend fun getPhotos(offset: Int, limit: Int = LIMIT): PhotoState {
        return try {
            val data = photosProvider.getPhotos(offset, limit)
                    .await()
                    .map { it.getData() }
            PhotoState.Data(data)
        } catch (e:Exception) {
            PhotoState.Error(e)
        }
    }
}