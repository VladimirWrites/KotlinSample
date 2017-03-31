package com.vlad1m1r.kotlintest.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment


/**
 * Created by vladimirjovanovic on 12/21/16.
 */

abstract class BaseFragment<P : IBasePresenter> : Fragment() {

    interface IFragmentHolder {
        fun refreshFragment()
    }

    var mPresenter: P? = null

    protected var mFragmentHolder: IFragmentHolder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = retainInstance()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mFragmentHolder = context as IFragmentHolder?
    }

    override fun onDetach() {
        super.onDetach()
        mFragmentHolder = null
    }

    override fun onStart() {
        super.onStart()
        presenter?.onStart()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    protected fun retainInstance(): Boolean {
        return true
    }

    val presenter: IBasePresenter?
        get() = mPresenter

}
