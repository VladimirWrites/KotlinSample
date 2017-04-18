package com.vlad1m1r.kotlintest.presentation.base

import android.support.annotation.StringRes


/**
 * Created by djordjehrnjez on 13/03/2017.
 */

interface IViewError : IBaseView {
    fun showProgress(show: Boolean)
    fun showError(@StringRes error: Int)
    fun showEmptyView()
}
