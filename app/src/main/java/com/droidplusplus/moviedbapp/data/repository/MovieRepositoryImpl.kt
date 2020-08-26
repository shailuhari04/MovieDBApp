package com.droidplusplus.moviedbapp.data.repository

import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.data.model.Review
import com.droidplusplus.moviedbapp.data.model.response.MovieCreditResponse
import com.droidplusplus.moviedbapp.data.remote.ApiService

class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun getNowPlayingMovieAsync(hashMap: HashMap<String, String>): List<Movie>? {
        return apiService.getNowPlayingMovieAsync(hashMap = hashMap).results
    }

    override suspend fun getMovieAsync(movieId: String): Movie {
        return apiService.getMovieAsync(movieId = movieId)
    }

    override suspend fun getMovieReviewsAsync(movieId: String): List<Review>? {
        return apiService.getMovieReviewsAsync(movieId = movieId).results
    }

    override suspend fun getMovieCreditsAsync(movieId: String): MovieCreditResponse {
        return apiService.getMovieCreditsAsync(movieId = movieId)
    }

    override suspend fun getMovieSimilarAsync(
        movieId: String,
        hashMap: HashMap<String, String>
    ): List<Movie>? {
        return apiService.getMovieSimilarAsync(movieId = movieId, hashMap = hashMap).results
    }
}