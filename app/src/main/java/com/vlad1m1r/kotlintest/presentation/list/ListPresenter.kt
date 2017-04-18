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