package com.ifs21004.myrecycleview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Novel(
    var name: String,
    var image: Int,
    var desc: String,
    var author: String,
    var year: String,
    var rating: String,
    var genre: String
) : Parcelable {

}