package com.vlad1m1r.kotlintest.presentation.list


import android.support.annotation.StringRes
import com.vlad1m1r.kotlintest.data.models.ItemPhoto
import com.vlad1m1r.kotlintest.presentation.base.IBasePresenter
import com.vlad1m1r.kotlintest.presentation.base.IViewError

import java.util.ArrayList

interface ListContract {

    interface Presenter : IBasePresenter {
        fun loadData(offset:Int = 0)
        fun loadingDataError(@StringRes error: Int)
    }

    interface View : IViewError {
        fun setPresenter(presenter: Presenter)
        fun loadData()
        fun showList(list: ArrayList<ItemPhoto>)
        fun addList(list: ArrayList<ItemPhoto>)
    }
}