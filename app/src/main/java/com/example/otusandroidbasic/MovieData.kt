package com.example.otusandroidbasic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
// подробная информация о фильме
class MovieData(val title: Int, val img: Int, val description: Int) : Parcelable {
}