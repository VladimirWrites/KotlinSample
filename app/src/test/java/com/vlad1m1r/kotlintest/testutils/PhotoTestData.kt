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

package com.vlad1m1r.kotlintest.testutils

import com.vlad1m1r.kotlintest.data.models.PhotoData
import com.vlad1m1r.kotlintest.domain.models.ItemPhoto
import io.reactivex.Observable

val PHOTO_DATA_1 = PhotoData(0, 0, "test1", "http://placehold.it/600/d32776")
val PHOTO_DATA_2 = PhotoData(0, 0, "test2", "http://placehold.it/600/d32777")
val PHOTO_DATA_3 = PhotoData(0, 0, "test3", "http://placehold.it/600/d32778")

val PHOTO_DATA_LIST = arrayListOf(PHOTO_DATA_1, PHOTO_DATA_2, PHOTO_DATA_3)

val PHOTO_DATA_OBSERVABLE = Observable.just(PHOTO_DATA_LIST)

val ITEM_PHOTO_1 = ItemPhoto(PHOTO_DATA_1.title, PHOTO_DATA_1.url)
val ITEM_PHOTO_2 = ItemPhoto(PHOTO_DATA_2.title, PHOTO_DATA_2.url)
val ITEM_PHOTO_3 = ItemPhoto(PHOTO_DATA_3.title, PHOTO_DATA_3.url)

val ITEM_PHOTO_LIST = arrayListOf(ITEM_PHOTO_1, ITEM_PHOTO_2, ITEM_PHOTO_3)

val ITEM_PHOTO_OBSERVABLE = Observable.just(ITEM_PHOTO_LIST)
