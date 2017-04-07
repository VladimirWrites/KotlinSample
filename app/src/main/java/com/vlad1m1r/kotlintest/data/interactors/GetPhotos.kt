package com.vlad1m1r.kotlintest.data.interactors

import com.vlad1m1r.kotlintest.data.ApiInterface
import com.vlad1m1r.kotlintest.data.models.ItemPhoto
import com.vlad1m1r.kotlintest.data.models.PhotoData
import io.reactivex.Observable

/**
 * Created by vladimirjovanovic on 3/24/17.
 */
class GetPhotos(val apiInterface: ApiInterface) {
    fun getPhotos(offset:Int, limit:Int):Observable<ArrayList<ItemPhoto>> {
        return this.apiInterface.getPhotos(offset, limit)
                .map({list:ArrayList<PhotoData> ->  formatData(list)})
    }

    fun formatData(photos:List<PhotoData>) : ArrayList<ItemPhoto> {
        val itemPhotos:ArrayList<ItemPhoto>  = ArrayList(photos.size)
        photos.mapTo(itemPhotos) { ItemPhoto(it.title.orEmpty(), it.url.orEmpty()) }
        return itemPhotos
    }
}