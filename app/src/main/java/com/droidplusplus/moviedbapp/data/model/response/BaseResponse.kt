package com.droidplusplus.moviedbapp.data.model.response

open class BaseResponse<Item>(
    val page: Int? = null,
    val total_results: Int? = null,
    val total_pages: Int? = null,
    var results: ArrayList<Item>? = null
)