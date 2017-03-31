package com.vlad1m1r.kotlintest.data

import com.vlad1m1r.kotlintest.data.models.PhotoData

import java.util.ArrayList

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by vladimirjovanovic on 6/28/16.
 */

interface ApiInterface {

    @GET("photos")
    fun getPhotos(@Query("_start") offset:Int, @Query("_limit") limit:Int): Observable<ArrayList<PhotoData>>

}
