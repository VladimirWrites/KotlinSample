package com.vlad1m1r.kotlintest.domain

interface Data <out T> {
    fun getData(): T
}