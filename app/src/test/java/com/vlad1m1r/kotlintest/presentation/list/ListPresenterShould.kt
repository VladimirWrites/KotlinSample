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

import com.nhaarman.mockitokotlin2.*
import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.domain.PhotoState
import com.vlad1m1r.kotlintest.domain.interactors.GetPhotos
import com.vlad1m1r.kotlintest.presentation.utils.TextContextProvider
import com.vlad1m1r.kotlintest.presentation.utils.CoroutineDisposable
import com.vlad1m1r.kotlintest.testutils.ITEM_PHOTO_LIST
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import java.net.UnknownHostException

class ListPresenterShould {

    private val view = mock<ListContract.View>()
    private val getPhotos = mock<GetPhotos>()
    private val disposables = mock<CoroutineDisposable>()
    private val coroutineContextProvider = TextContextProvider()

    private val presenter = ListPresenter(view, getPhotos, disposables, coroutineContextProvider)
    private val presenterMock = mock<ListPresenter>()

    @Test
    fun start() {
        doCallRealMethod().whenever(presenterMock).start()
        presenterMock.start()
        verify(presenterMock).loadData(0)
    }

    @Test
    fun loadDataOffsetZero() {
        whenever(runBlocking { getPhotos.getPhotos(any(), any()) }).thenReturn(PhotoState.Data(ITEM_PHOTO_LIST))
        presenter.loadData(0)
        verify(disposables).add(any())
        verify(view).showProgress(true)
        verify(view).showList(ITEM_PHOTO_LIST)
        verify(view).showProgress(false)
    }

    @Test
    fun loadDataOffsetNotZero() {
        whenever(runBlocking { getPhotos.getPhotos(any(), any()) }).thenReturn(PhotoState.Data(ITEM_PHOTO_LIST))
        presenter.loadData(20)
        verify(view).addList(ITEM_PHOTO_LIST)
    }

    @Test
    fun loadDataEmpty() {
        whenever(runBlocking { getPhotos.getPhotos(any(), any()) }).thenReturn(PhotoState.Data(ArrayList()))
        presenter.loadData(0)
        verify(view).showEmptyView()
    }

    @Test
    fun loadDataErrorUnknownHostException() {
        whenever(runBlocking { getPhotos.getPhotos(any(), any()) }).thenReturn(PhotoState.Error(UnknownHostException()))
        presenter.loadData(0)
        verify(view).showError(R.string.error__check_internet_connection)
    }

    @Test
    fun loadDataErrorException() {
        whenever(runBlocking { getPhotos.getPhotos(any(), any()) }).thenReturn(PhotoState.Error(Exception()))
        presenter.loadData(0)
        verify(view).showError(R.string.error__request_error)
    }

    @Test
    fun onDestroy() {
        presenter.onDestroy()
        verify(disposables).dispose()
    }
}