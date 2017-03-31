package com.vlad1m1r.kotlintest.presentation.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.vlad1m1r.kotlintest.R


/**
 * Created by vladimirjovanovic on 10/19/15.
 */
abstract class BaseMvpActivity<P : IBasePresenter, F : BaseFragment<P>> : BaseActivity(), BaseFragment.IFragmentHolder {

    open var mPresenter: P? = null

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
            mPresenter = fragment.presenter as P
        } else {
            mPresenter = getPresenter(fragment)
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