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
        if (layoutManager is GridLayoutManager) {
            this.gridLayoutManager = layoutManager
        } else if (layoutManager is LinearLayoutManager) {
            this.linearLayoutManager = layoutManager
        } else if (layoutManager is StaggeredGridLayoutManager) {
            this.staggeredGridLayoutManager = layoutManager
            spanArray = IntArray(staggeredGridLayoutManager!!.spanCount)
        } else {
            throw IllegalArgumentException("Selected LayoutManager has not supported")
        }
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView!!.childCount

        if (linearLayoutManager != null) {
            totalItemCount = linearLayoutManager!!.itemCount
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        } else if (gridLayoutManager != null) {
            totalItemCount = gridLayoutManager!!.itemCount
            firstVisibleItem = gridLayoutManager!!.findFirstVisibleItemPosition()
        } else if (staggeredGridLayoutManager != null) {
            totalItemCount = staggeredGridLayoutManager!!.itemCount
            firstVisibleItem = staggeredGridLayoutManager!!.findFirstVisibleItemPositions(spanArray)[0]
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
