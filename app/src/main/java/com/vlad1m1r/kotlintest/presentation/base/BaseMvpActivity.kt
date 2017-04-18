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

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.vlad1m1r.kotlintest.R

abstract class BaseMvpActivity<P : IBasePresenter, F : BaseFragment<P>> : BaseActivity(), BaseFragment.IFragmentHolder {

    open var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        var fragment: F? = supportFragmentManager
                .findFragmentById(R.id.content_frame) as F?

        if (fragment == null) {
            fragment = this.getFragment()

            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.content_frame, fragment)
            transaction.commit()
        }

        if (fragment.presenter != null) {
            presenter = fragment.presenter as P
        } else {
            presenter = getPresenter(fragment)
        }
    }

    fun setupToolbar(homeAsUp: Boolean, toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(homeAsUp)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun refreshFragment() {

    }

    protected val layoutId: Int
        @LayoutRes
        get() = R.layout.activity_base

    abstract fun getFragment(): F

    abstract fun getPresenter(fragment: F): P

}