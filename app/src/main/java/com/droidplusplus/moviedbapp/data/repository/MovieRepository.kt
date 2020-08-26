package com.droidplusplus.moviedbapp.data.repository

import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.data.model.Review
import com.droidplusplus.moviedbapp.data.model.response.MovieCreditResponse

interface MovieRepository {

    suspend fun getNowPlayingMovieAsync(hashMap: java.util.HashMap<String, String>): List<Movie>?

    suspend fun getMovieAsync(movieId: String): Movie

    suspend fun getMovieReviewsAsync(movieId: String): List<Review>?

    suspend fun getMovieCreditsAsync(movieId: String): MovieCreditResponse

    suspend fun getMovieSimilarAsync(
        movieId: String,
        hashMap: HashMap<String, String>
    ): List<Movie>?
}