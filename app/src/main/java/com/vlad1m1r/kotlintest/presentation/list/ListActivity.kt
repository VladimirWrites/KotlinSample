package com.vlad1m1r.kotlintest.presentation.list

import com.vlad1m1r.kotlintest.data.ApiClient
import com.vlad1m1r.kotlintest.data.interactors.GetPhotos
import com.vlad1m1r.kotlintest.presentation.base.BaseMvpActivity


/**
 * Created by vladimirjovanovic on 3/24/17.
 */

class ListActivity : BaseMvpActivity<ListContract.Presenter, ListFragment>() {

    override fun getFragment(): ListFragment {
        return ListFragment.newInstance()
    }

    override fun getPresenter(fragment: ListFragment): ListContract.Presenter {
        return ListPresenter(fragment as ListContract.View, GetPhotos(ApiClient().services!!))
    }
}
