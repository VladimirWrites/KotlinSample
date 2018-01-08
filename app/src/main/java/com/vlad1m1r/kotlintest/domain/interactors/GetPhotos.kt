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

import com.vlad1m1r.kotlintest.domain.models.ItemPhoto
import io.reactivex.Observable

class GetPhotos(private var photosProvider: (offset: Int, limit: Int) -> Observable<ArrayList<ItemPhoto>>) {
    fun getPhotos(offset: Int, limit: Int): Observable<ArrayList<ItemPhoto>>
            = photosProvider(offset, limit)
}