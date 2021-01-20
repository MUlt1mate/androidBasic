package com.example.otusandroidbasic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
// пользовательский ответ
class FeedbackData(val comment: String, val like: Boolean) : Parcelable {
}