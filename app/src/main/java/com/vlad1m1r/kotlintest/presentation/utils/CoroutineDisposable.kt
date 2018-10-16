package com.vlad1m1r.kotlintest.presentation.utils

import kotlinx.coroutines.experimental.Job

class CoroutineDisposable {
    val disposables: ArrayList<Job> = ArrayList()
    fun add(job: Job) = disposables.add(job)
    fun dispose() = disposables.forEach{ it.cancel() }
}