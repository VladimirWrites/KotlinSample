package com.vlad1m1r.kotlintest.presentation.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * Created by vladimirjovanovic on 3/17/17.
 */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
