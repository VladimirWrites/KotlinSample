package com.vlad1m1r.kotlintest.di

import com.vlad1m1r.kotlintest.presentation.utils.CoroutineContextProvider
import com.vlad1m1r.kotlintest.presentation.utils.CoroutineDisposable
import org.koin.dsl.module.module

val coroutineModule = module {
    single { CoroutineContextProvider() }
    factory { CoroutineDisposable() }
}