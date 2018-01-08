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
import com.vlad1m1r.kotlintest.domain.interactors.GetPhotos
import com.vlad1m1r.kotlintest.domain.models.ItemPhoto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import java.util.*

class ListPresenter(private val view: ListContract.View, private val getPhotos: GetPhotos, private val disposables: CompositeDisposable) : ListContract.Presenter {

    val LIMIT = 20

    init {
        this.view.setPresenter(this)
    }

    override fun start() {
        loadData(0)
    }

    override fun onDestroy() {
        disposables.clear()
    }

    override fun loadData(offset: Int) {
        if (offset == 0) view.showProgress(true)
        disposables.add(
                getPhotos
                        .getPhotos(offset, LIMIT)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .single(ArrayList())
                        .subscribeWith(
                                object : DisposableSingleObserver<ArrayList<ItemPhoto>>() {
                                    override fun onSuccess(photos: ArrayList<ItemPhoto>) {
                                        if (photos.size > 0) {
                                            if (offset > 0) {
                                                view.addList(photos)
                                            } else {
                                                view.showList(photos)
                                                view.showProgress(false)
                                            }

                                        } else {
                                            view.showEmptyView()
                                        }
                                    }

                                    override fun onError(e: Throwable) {
                                        view.showList(ArrayList())
                                        when (e) {
                                            is UnknownHostException -> view.showError(R.string.error__check_internet_connection)
                                            else -> view.showError(R.string.error__request_error)
                                        }
                                    }

                                }
                        )
        )
    }
}