package com.vlad1m1r.kotlintest.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by vladimirjovanovic on 3/24/17.
 */

class PhotoData {
    @SerializedName("albumId")
    val albumId: Int = 0
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("title")
    val title: String? = null
    @SerializedName("url")
    val url: String? = null
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String? = null
}
