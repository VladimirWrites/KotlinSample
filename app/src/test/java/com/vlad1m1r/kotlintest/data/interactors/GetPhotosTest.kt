package com.vlad1m1r.kotlintest.data.interactors

import com.vlad1m1r.kotlintest.data.models.PhotoData
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by vladimirjovanovic on 4/6/17.
 */
class GetPhotosTest {

    @Test
    fun formatData() {
        val getPhotos:GetPhotos = GetPhotos()

        val photosData:ArrayList<PhotoData> = arrayListOf()

        (0..9).mapTo(photosData) { PhotoData(100+ it, it, "test"+ it, "url"+ it, "thumbnail"+ it) }

        val photos = getPhotos.formatData(photosData)
        for(i:Int in 0..9) {
            val photo = photos[i]
            assertEquals(photo.name, "test"+i)
            assertEquals(photo.url, "url"+i)
        }
    }
}