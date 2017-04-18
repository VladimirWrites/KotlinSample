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

package com.vlad1m1r.kotlintest.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment<P : IBasePresenter> : Fragment() {

    interface IFragmentHolder {
        fun refreshFragment()
    }

    open var presenter: P? = null

    protected var fragmentHolder: IFragmentHolder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = retainInstance()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        fragmentHolder = context as IFragmentHolder?
    }

    override fun onDetach() {
        super.onDetach()
        fragmentHolder = null
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
}
