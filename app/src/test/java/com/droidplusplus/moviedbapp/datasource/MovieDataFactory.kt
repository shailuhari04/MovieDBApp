package com.droidplusplus.moviedbapp.datasource

import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.data.model.response.MovieListResponse

fun createMovieListResponse(): MovieListResponse {
    val response = MovieListResponse()
    val movie1 = Movie(id = "1", title = "The Lion")
    val movie2 = Movie(id = "2", title = "Love you Jaan")
    val movie3 = Movie(id = "3", title = "Jani dushman")
    val movie4 = Movie(id = "4", title = "Rain in the Garden")
    val movie5 = Movie(id = "5", title = "Dil garden")
    response.results = arrayListOf(movie1, movie2, movie3, movie4, movie5)

    return response
}