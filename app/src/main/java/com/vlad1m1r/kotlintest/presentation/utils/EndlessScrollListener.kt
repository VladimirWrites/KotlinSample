package com.vlad1m1r.kotlintest.presentation.utils

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class EndlessScrollListener(layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    private var mPreviousTotal = 0 // The total number of items in the dataset after the last load
    private var mLoading = true // True if we are still waiting for the last set of data to load.
    private val mVisibleThreshold = 5 // The minimum amount of items to have below your current scroll position before mLoading more.
    private var mFirstVisibleItem: Int = 0
    private var mVisibleItemCount: Int = 0
    private var mTotalItemCount: Int = 0

    private var mStartPageNumber = 0
    var currentPage = 0

    private var mScrollPosition = 0

    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mGridLayoutManager: GridLayoutManager? = null
    private var mStaggeredGridLayoutManager: StaggeredGridLayoutManager? = null

    private var mSpanArray: IntArray? = null

    init {
        if (layoutManager is GridLayoutManager) {
            this.mGridLayoutManager = layoutManager
        } else if (layoutManager is LinearLayoutManager) {
            this.mLinearLayoutManager = layoutManager
        } else if (layoutManager is StaggeredGridLayoutManager) {
            this.mStaggeredGridLayoutManager = layoutManager
            mSpanArray = IntArray(mStaggeredGridLayoutManager!!.spanCount)
        } else {
            throw IllegalArgumentException("Selected LayoutManager has not supported")
        }
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        mVisibleItemCount = recyclerView!!.childCount

        if (mLinearLayoutManager != null) {
            mTotalItemCount = mLinearLayoutManager!!.itemCount
            mFirstVisibleItem = mLinearLayoutManager!!.findFirstVisibleItemPosition()
        } else if (mGridLayoutManager != null) {
            mTotalItemCount = mGridLayoutManager!!.itemCount
            mFirstVisibleItem = mGridLayoutManager!!.findFirstVisibleItemPosition()
        } else if (mStaggeredGridLayoutManager != null) {
            mTotalItemCount = mStaggeredGridLayoutManager!!.itemCount
            mFirstVisibleItem = mStaggeredGridLayoutManager!!.findFirstVisibleItemPositions(mSpanArray)[0]
        }

        mScrollPosition = recyclerView.computeVerticalScrollOffset()
        onScroll(mFirstVisibleItem, dy, mScrollPosition)

        if (mLoading) {
            if (mTotalItemCount > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = mTotalItemCount
            }
        }
        if (!mLoading && mTotalItemCount - mVisibleItemCount <= mFirstVisibleItem + mVisibleThreshold) {
            // End has been reached

            // Do something
            currentPage++

            onLoadMore(currentPage, mTotalItemCount)

            mLoading = true
        }
    }

    fun reset() {
        currentPage = mStartPageNumber
        this.mPreviousTotal = 0
        this.mLoading = true
    }

    fun setStartPageNumber(startPageNumber: Int, restart: Boolean) {
        this.mStartPageNumber = startPageNumber
        if (restart)
            reset()
    }

    abstract fun onLoadMore(currentPage: Int, totalItemCount: Int)
    abstract fun onScroll(firstVisibleItem: Int, dy: Int, scrollPosition: Int)

    companion object {
        var TAG = EndlessScrollListener::class.java.simpleName
    }
}
