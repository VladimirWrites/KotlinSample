package com.vlad1m1r.kotlintest.presentation.base


/**
 * Created by djordjehrnjez on 13/03/2017.
 */

interface IViewError : IBaseView {
    fun showProgress(show: Boolean)
    fun showError(error: Int)
    fun showEmptyView()
}
