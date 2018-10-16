package com.vlad1m1r.kotlintest.domain

import com.vlad1m1r.kotlintest.domain.models.PhotoData

sealed class PhotoState {
    class Data(val data: List<PhotoData>): PhotoState()
    class Error(val exception: Exception): PhotoState()
}