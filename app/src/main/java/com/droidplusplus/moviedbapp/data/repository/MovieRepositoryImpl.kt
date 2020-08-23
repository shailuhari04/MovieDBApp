package com.droidplusplus.moviedbapp.data.repository

import com.droidplusplus.moviedbapp.data.model.response.MovieListResponse
import com.droidplusplus.moviedbapp.data.remote.ApiService

class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun fetchMovieList(hashMap: HashMap<String, String>): MovieListResponse {
        TODO("Not yet implemented")
    }
}