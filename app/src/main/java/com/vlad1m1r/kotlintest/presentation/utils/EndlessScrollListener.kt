package com.vlad1m1r.kotlintest.presentation.utils

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class EndlessScrollListener(layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold = 5 // The minimum amount of items to have below your current scroll position before loading more.
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    private var startPageNumber = 0
    var currentPage = 0

    private var scrollPosition = 0

    private var linearLayoutManager: LinearLayoutManager? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var staggeredGridLayoutManager: StaggeredGridLayoutManager? = null

    private var spanArray: IntArray? = null

    init {
        when (layoutManager) {
            is GridLayoutManager -> this.gridLayoutManager = layoutManager
            is LinearLayoutManager -> this.linearLayoutManager = layoutManager
            is StaggeredGridLayoutManager -> {
                this.staggeredGridLayoutManager = layoutManager
                spanArray = IntArray(staggeredGridLayoutManager!!.spanCount)
            }
            else -> throw IllegalArgumentException("Selected LayoutManager has not supported")
        }
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView!!.childCount

        // End has been reached

        // Do something
        when {
            linearLayoutManager != null -> {
                totalItemCount = linearLayoutManager!!.itemCount
                firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
            }
            gridLayoutManager != null -> {
                totalItemCount = gridLayoutManager!!.itemCount
                firstVisibleItem = gridLayoutManager!!.findFirstVisibleItemPosition()
            }
            staggeredGridLayoutManager != null -> {
                totalItemCount = staggeredGridLayoutManager!!.itemCount
                firstVisibleItem = staggeredGridLayoutManager!!.findFirstVisibleItemPositions(spanArray)[0]
            }
        }

        scrollPosition = recyclerView.computeVerticalScrollOffset()
        onScroll(firstVisibleItem, dy, scrollPosition)

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            // End has been reached

            // Do something
            currentPage++

            onLoadMore(currentPage, totalItemCount)

            loading = true
        }
    }

    fun reset() {
        currentPage = startPageNumber
        this.previousTotal = 0
        this.loading = true
    }

    fun setStartPageNumber(startPageNumber: Int, restart: Boolean) {
        this.startPageNumber = startPageNumber
        if (restart)
            reset()
    }

    abstract fun onLoadMore(currentPage: Int, totalItemCount: Int)
    abstract fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int)

    companion object {
        var TAG = EndlessScrollListener::class.java.simpleName
    }
}
