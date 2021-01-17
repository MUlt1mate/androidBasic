package com.example.otusandroidbasic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieData(val title: String, val img: String, val description: String) : Parcelable {
}