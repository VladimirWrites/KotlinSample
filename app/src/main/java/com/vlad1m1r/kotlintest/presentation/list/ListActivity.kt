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

import android.os.Bundle
import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.data.ApiClient
import com.vlad1m1r.kotlintest.data.ApiInterface
import com.vlad1m1r.kotlintest.data.providers.PhotosProvider
import com.vlad1m1r.kotlintest.domain.interactors.GetPhotos
import com.vlad1m1r.kotlintest.presentation.base.BaseActivity
import io.reactivex.disposables.CompositeDisposable

class ListActivity : BaseActivity() {

    var presenter: ListContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        var fragment = supportFragmentManager.findFragmentById(R.id.content_frame)

        if (fragment == null) {
            fragment = this.getFragment()

            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.content_frame, fragment)
            transaction.commit()
        }

        if (fragment is ListFragment) {
            presenter = if (fragment.presenter != null) {
                fragment.presenter
            } else {
                getPresenter(fragment)
            }
        }
    }

    fun getFragment(): ListFragment = ListFragment.newInstance()

    fun getPresenter(fragment: ListFragment): ListContract.Presenter {
        val apiInterface: ApiInterface = ApiClient().services
        return ListPresenter(fragment as ListContract.View,
                GetPhotos(PhotosProvider(apiInterface).getPhotos),
                CompositeDisposable())
    }
}
