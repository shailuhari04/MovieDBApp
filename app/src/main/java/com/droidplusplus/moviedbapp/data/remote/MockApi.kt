package com.droidplusplus.moviedbapp.data.remote

import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.data.model.response.MovieCreditResponse
import com.droidplusplus.moviedbapp.data.model.response.MovieListResponse
import com.droidplusplus.moviedbapp.data.model.response.MovieReviewResponse
import com.droidplusplus.moviedbapp.data.model.response.SimilarMovieListResponse
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MockApi(
) : ApiService {

    override suspend fun getNowPlayingMovieAsync(
        hashMap: HashMap<String, String>
    ): MovieListResponse =
        when (HttpURLConnection.HTTP_UNAUTHORIZED) {
            1 -> {
                throw BaseException.toNetworkError(
                    cause = UnknownHostException()
                )
            }

            2 -> {
                throw BaseException.toNetworkError(
                    cause = SocketTimeoutException()
                )
            }

            HttpURLConnection.HTTP_OK -> {
                MovieListResponse()
            }

            HttpURLConnection.HTTP_UNAUTHORIZED -> {
                throw BaseException.toServerError(
                    serverErrorResponse = "Test code 401",
                    response = null,
                    httpCode = HttpURLConnection.HTTP_UNAUTHORIZED
                )
            }

            HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                throw BaseException.toServerError(
                    serverErrorResponse = "Test code 500",
                    response = null,
                    httpCode = HttpURLConnection.HTTP_INTERNAL_ERROR
                )
            }

            else -> MovieListResponse()
        }


    override suspend fun getMovieAsync(
        movieId: String
    ): Movie = Movie(movieId)

    override suspend fun getMovieReviewsAsync(movieId: String): MovieReviewResponse {
        return MovieReviewResponse()
    }

    override suspend fun getMovieCreditsAsync(movieId: String): MovieCreditResponse {
        return MovieCreditResponse()
    }

    override suspend fun getMovieSimilarAsync(
        movieId: String,
        hashMap: HashMap<String, String>
    ): SimilarMovieListResponse {
        return SimilarMovieListResponse()
    }
}