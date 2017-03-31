package com.vlad1m1r.kotlintest

import android.app.Application

import com.vlad1m1r.kotlintest.data.ApiClient
import com.vlad1m1r.kotlintest.data.ApiInterface


/**
 * Created by vladimirjovanovic on 3/23/17.
 */


class AppConfig : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {

        internal val mApiClient: ApiClient = ApiClient()

        val restService: ApiInterface
            get() {
                return mApiClient.services!!
            }
    }
}
