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

import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.domain.PhotoState
import com.vlad1m1r.kotlintest.domain.interactors.GetPhotos
import com.vlad1m1r.kotlintest.presentation.utils.CoroutineContextProvider
import com.vlad1m1r.kotlintest.presentation.utils.CoroutineDisposable
import kotlinx.coroutines.experimental.*
import java.net.UnknownHostException
import java.util.*

class ListPresenter(
        private val view: ListContract.View,
        private val getPhotos: GetPhotos,
        private val disposables: CoroutineDisposable,
        private val coroutineContextProvider: CoroutineContextProvider) : ListContract.Presenter {

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
        loadData(0)
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    override fun loadData(offset: Int) {
        disposables.add(loadDataAsync(offset))
    }

    private fun loadDataAsync(offset: Int) = GlobalScope.launch(coroutineContextProvider.Main) {
        if (offset == 0) view.showProgress(true)
        val photoState = GlobalScope.async(coroutineContextProvider.IO) {
            getPhotos.getPhotos(offset)
        }.await()

        when (photoState) {
            is PhotoState.Data -> {
                if (photoState.data.isNotEmpty()) {
                    if (offset > 0) {
                        view.addList(photoState.data)
                    } else {
                        view.showList(photoState.data)
                        view.showProgress(false)
                    }
                } else {
                    view.showEmptyView()
                    view.showProgress(false)
                }
            }
            is PhotoState.Error -> {
                view.showList(ArrayList())
                when (photoState.exception) {
                    is UnknownHostException -> view.showError(R.string.error__check_internet_connection)
                    else -> view.showError(R.string.error__request_error)
                }
            }
        }
    }
}