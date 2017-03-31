package com.vlad1m1r.kotlintest.presentation.list


import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.data.interactors.GetPhotos
import com.vlad1m1r.kotlintest.data.models.ItemPhoto

import java.util.ArrayList

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListPresenter(private val mView: ListContract.View) : ListContract.Presenter {

    val LIMIT = 20

    internal var mGetPhotos: GetPhotos

    private val mDisposables = CompositeDisposable()

    init {
        this.mView.setPresenter(this)
        this.mGetPhotos = GetPhotos()
    }

    override fun onStart() {}

    override fun onDestroy() {
        mDisposables.clear()
    }

    override fun loadData(offset:Int) {
        if(offset == 0) mView.showProgress(true)
        mDisposables.add(
                mGetPhotos.getPhotos(offset, LIMIT)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .single(ArrayList<ItemPhoto>())
                        .subscribeWith(
                                object : DisposableSingleObserver<ArrayList<ItemPhoto>>() {

                                    override fun onSuccess(photos: ArrayList<ItemPhoto>) {
                                        if (photos.size > 0) {
                                            if(offset > 0) {
                                                mView.addList(photos)
                                            } else {
                                                mView.showList(photos)
                                                mView.showProgress(false)
                                            }

                                        } else {
                                            mView.showEmptyView()
                                        }
                                    }

                                    override fun onError(e: Throwable) {
                                        mView.showError(R.string.error__request_error)
                                    }
                                }
                        )
        )
    }
}