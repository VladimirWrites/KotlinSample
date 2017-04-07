package com.vlad1m1r.kotlintest.presentation.list

import android.os.Bundle
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

class ListFragment : BaseFragment<ListContract.Presenter>(), ListContract.View, SwipeRefreshLayout.OnRefreshListener {

    var endlessScrollListener: EndlessScrollListener? = null
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
    }

    override fun loadData() {
        if (NetworkUtils.isNetworkConnected(context))
            this.presenter?.loadData()
        else
            showError(R.string.error__no_internet_connection)
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

    override fun showError(error: Int) {
        viewEmpty.visibility = View.GONE
        viewError.visibility = View.VISIBLE
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
        this.endlessScrollListener?.reset()
        this.presenter?.loadData()
    }

    companion object {

        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}