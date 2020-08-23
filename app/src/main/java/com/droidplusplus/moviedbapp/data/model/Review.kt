package com.droidplusplus.moviedbapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    val id: String,
    val author: String? = null,
    val content: String? = null,
    val url: String? = null,
) : Parcelable