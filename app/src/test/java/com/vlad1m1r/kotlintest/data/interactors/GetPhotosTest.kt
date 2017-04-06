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

        (0..3).mapTo(photosData) {
            PhotoData(
                    albumId = 100 + it,
                    id = it,
                    title = "test" + it,
                    url = "url" + it,
                    thumbnailUrl = "thumbnail" + it
            )
        }

        val photos = getPhotos.formatData(photosData)
        for(i:Int in 0..3) {
            val photo = photos[i]
            assertEquals(photo.name, "test"+i)
            assertEquals(photo.url, "url"+i)
        }

        val photosDataEmpty:ArrayList<PhotoData> = arrayListOf()

        (0..3).mapTo(photosDataEmpty) {
            PhotoData(
                    albumId = 100 + it,
                    id = it,
                    thumbnailUrl = "thumbnail" + it
            )
        }

        val photosEmpty = getPhotos.formatData(photosDataEmpty)
        for(i:Int in 0..3) {
            val photo = photosEmpty[i]
            assertEquals(photo.name, "")
            assertEquals(photo.url, "")
        }
    }
}