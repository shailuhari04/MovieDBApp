package com.droidplusplus.moviedbapp.data.repository

import com.droidplusplus.moviedbapp.data.model.response.MovieListResponse

interface MovieRepository {

    suspend fun fetchMovieList(
        hashMap: HashMap<String, String> = HashMap()
    ): MovieListResponse
}