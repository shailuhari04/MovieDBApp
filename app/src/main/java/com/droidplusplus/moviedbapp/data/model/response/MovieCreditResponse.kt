package com.droidplusplus.moviedbapp.data.model.response

import android.os.Parcelable
import com.droidplusplus.moviedbapp.BuildConfig
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCreditResponse(
    val id: Int? = null,
    val cast: ArrayList<Cast>? = null,
    val crew: ArrayList<Crew>? = null
) : Parcelable


@Parcelize
data class Cast(
    val cast_id: String?,
    val character: String?,
    val credit_id: String?,
    val gender: Int?,
    val id: String?,
    val name: String?,
    val order: Int?,
    val profile_path: String?
) : Parcelable {

    fun getFullProfilePath() = takeIf { profile_path.isNullOrBlank() }?.let { null }
        ?: BuildConfig.SMALL_IMAGE_URL + profile_path
}


@Parcelize
data class Crew(
    val credit_id: String?,
    val department: String?,
    val gender: Int?,
    val id: String?,
    val job: String?,
    val name: String?,
    val profile_path: String?
) : Parcelable {

    fun getFullProfilePath() = takeIf { profile_path.isNullOrBlank() }?.let { null }
        ?: BuildConfig.SMALL_IMAGE_URL + profile_path
}