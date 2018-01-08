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

import com.nhaarman.mockito_kotlin.*
import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.domain.interactors.GetPhotos
import com.vlad1m1r.kotlintest.testutils.ITEM_PHOTO_LIST
import com.vlad1m1r.kotlintest.testutils.ITEM_PHOTO_OBSERVABLE
import com.vlad1m1r.kotlintest.testutils.RxImmediateSchedulerRule
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class ListPresenterTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    val view = mock<ListContract.View>()
    val getPhotos = mock<GetPhotos>()
    val disposables = mock<CompositeDisposable>()

    val presenter = ListPresenter(view, getPhotos, disposables)
    val presenterMock = mock<ListPresenter>()

    @Test
    fun start() {
        doCallRealMethod().whenever(presenterMock).start()
        presenterMock.start()
        verify(presenterMock).loadData(0)
    }

    @Test
    fun loadDataOffsetZero() {
        whenever(getPhotos.getPhotos(any(), any())).thenReturn(ITEM_PHOTO_OBSERVABLE)
        presenter.loadData(0)
        verify(disposables).add(any())
        verify(view).showProgress(true)
        verify(view).showList(ITEM_PHOTO_LIST)
        verify(view).showProgress(false)
    }

    @Test
    fun loadDataOffsetNotZero() {
        whenever(getPhotos.getPhotos(any(), any())).thenReturn(ITEM_PHOTO_OBSERVABLE)
        presenter.loadData(20)
        verify(view).addList(ITEM_PHOTO_LIST)
    }

    @Test
    fun loadDataEmpty() {
        whenever(getPhotos.getPhotos(any(), any())).thenReturn(Observable.just(ArrayList()))
        presenter.loadData(0)
        verify(view).showEmptyView()
    }

    @Test
    fun loadDataErrorUnknownHostException() {
        whenever(getPhotos.getPhotos(any(), any())).thenReturn(Observable.error(UnknownHostException()))
        presenter.loadData(0)
        verify(view).showError(R.string.error__check_internet_connection)
    }

    @Test
    fun loadDataErrorException() {
        whenever(getPhotos.getPhotos(any(), any())).thenReturn(Observable.error(Exception()))
        presenter.loadData(0)
        verify(view).showError(R.string.error__request_error)
    }

    @Test
    fun onDestroy() {
        presenter.onDestroy()
        verify(disposables).clear()
    }
}