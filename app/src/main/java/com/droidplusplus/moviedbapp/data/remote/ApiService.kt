package com.droidplusplus.moviedbapp.data.remote

import com.droidplusplus.moviedbapp.data.model.*
import com.droidplusplus.moviedbapp.data.model.response.MovieCreditResponse
import com.droidplusplus.moviedbapp.data.model.response.MovieListResponse
import com.droidplusplus.moviedbapp.data.model.response.MovieReviewResponse
import com.droidplusplus.moviedbapp.data.model.response.SimilarMovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovieAsync(@QueryMap hashMap: HashMap<String, String> = HashMap()): MovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieAsync(@QueryMap hashMap: HashMap<String, String> = HashMap()): Movie

    @GET("3/movie/{movie_id}/reviews")
    suspend fun getMovieReviewsAsync(@Path("movie_id") movieId: String): MovieReviewResponse

    @GET("3/movie/{movie_id}/credits")
    suspend fun getMovieCreditsAsync(@Path("movie_id") movieId: String): MovieCreditResponse

    @GET("3/movie/{movie_id}/similar")
    suspend fun getMovieSimilarAsync(@QueryMap hashMap: HashMap<String, String> = HashMap()): SimilarMovieListResponse
}

object ApiParams {
    const val PAGE = "page"
}