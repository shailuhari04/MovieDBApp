package com.droidplusplus.moviedbapp.data.model

import android.os.Parcelable
import com.droidplusplus.moviedbapp.BuildConfig
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val backdrop_path: String? = null,
    val homepage: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val status: String? = null,
    val title: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null,
) : Parcelable {

    fun getBackdropPath() = takeIf { backdrop_path.isNullOrBlank() }?.let { null }
        ?: BuildConfig.SMALL_IMAGE_URL + backdrop_path

    fun getPosterPath() = takeIf { poster_path.isNullOrBlank() }?.let { null }
        ?: BuildConfig.SMALL_IMAGE_URL + poster_path
}