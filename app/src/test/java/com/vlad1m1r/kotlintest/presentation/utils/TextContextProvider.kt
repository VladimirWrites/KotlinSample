package com.vlad1m1r.kotlintest.presentation.utils

import kotlinx.coroutines.experimental.Dispatchers
import kotlin.coroutines.experimental.CoroutineContext

open class TextContextProvider: CoroutineContextProvider() {
    override  val Main: CoroutineContext by lazy { Dispatchers.Unconfined }
    override  val IO: CoroutineContext by lazy { Dispatchers.Unconfined }
}