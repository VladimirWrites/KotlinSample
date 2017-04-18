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

import android.support.annotation.StringRes
import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.data.interactors.GetPhotos
import com.vlad1m1r.kotlintest.data.models.ItemPhoto

import java.util.ArrayList

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListPresenter(private val view: ListContract.View, private val getPhotos: GetPhotos) : ListContract.Presenter {

    val LIMIT = 20

    private val disposables = CompositeDisposable()

    init {
        this.view.setPresenter(this)
    }

    override fun onStart() {}

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
                        .single(ArrayList<ItemPhoto>())
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
                                        view.showError(R.string.error__request_error)
                                    }
                                }
                        )
        )
    }

    override fun loadingDataError(@StringRes error: Int) {
        view.showList(ArrayList())
        view.showError(error)
    }
}