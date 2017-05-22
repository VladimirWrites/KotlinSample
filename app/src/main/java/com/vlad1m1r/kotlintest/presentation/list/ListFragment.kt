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
import android.support.annotation.StringRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.vlad1m1r.kotlintest.R
import com.vlad1m1r.kotlintest.data.models.ItemPhoto
import com.vlad1m1r.kotlintest.data.utils.NetworkUtils
import com.vlad1m1r.kotlintest.presentation.base.BaseFragment
import com.vlad1m1r.kotlintest.presentation.utils.EndlessScrollListener

import java.util.ArrayList

import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.view_error.*

class ListFragment : BaseFragment<ListContract.Presenter>(), ListContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    lateinit var endlessScrollListener: EndlessScrollListener
    var listAdapter: ListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        // creates just in case that fragment was not retained or it's first onViewCreated call so listAdapter == null
        this.listAdapter = listAdapter ?: ListAdapter()
        recyclerView.adapter = listAdapter

        this.endlessScrollListener = object : EndlessScrollListener(recyclerView.layoutManager) {
            override fun onLoadMore(currentPage: Int, totalItemCount: Int) {
                if (totalItemCount > 1) {
                    presenter!!.loadData(totalItemCount)
                }
            }
            override fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int) {}
        }

        recyclerView.addOnScrollListener(endlessScrollListener)

        swipeRefresh.setOnRefreshListener(this)

        // loads data just in case that fragment was not retained or it's first onViewCreated call
        if(listAdapter!!.list.size == 0) loadData()

        buttonTryAgain.setOnClickListener(this)
    }

    override fun loadData() {
        if (NetworkUtils.isNetworkConnected(context))
            this.presenter?.loadData()
        else
            this.presenter?.loadingDataError(R.string.error__no_internet_connection)
    }

    override fun setPresenter(presenter: ListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showList(list: ArrayList<ItemPhoto>) {
        (recyclerView.adapter as ListAdapter).list = list
    }

    override fun addList(list: ArrayList<ItemPhoto>) {
        (recyclerView.adapter as ListAdapter).addList(list)
    }

    override fun showProgress(show: Boolean) {
        swipeRefresh.isRefreshing = show
        viewEmpty.visibility = View.GONE
        viewError.visibility = View.GONE
        swipeRefresh.visibility = View.VISIBLE
    }

    override fun showError(@StringRes error: Int) {
        viewEmpty.visibility = View.GONE
        viewError.visibility = View.VISIBLE
        textMessageError.setText(error)
        swipeRefresh.visibility = View.GONE
        swipeRefresh.isRefreshing = false
    }

    override fun showEmptyView() {
        viewEmpty.visibility = View.VISIBLE
        viewError.visibility = View.GONE
        swipeRefresh.visibility = View.GONE
        swipeRefresh.isRefreshing = false
    }

    override fun onRefresh() {
        this.endlessScrollListener.reset()
        loadData()
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.buttonTryAgain -> loadData()
        }
    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}