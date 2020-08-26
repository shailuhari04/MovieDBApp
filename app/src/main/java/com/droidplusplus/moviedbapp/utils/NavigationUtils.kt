package com.droidplusplus.moviedbapp.utils

import android.app.Activity
import android.content.Intent
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.ui.details.MovieDetailsActivity

/**
 * Helper Extension function
 * Navigation into MovieDetails Screen
 * @param movie
 */
fun Activity.navigateToMovieDetailsScreen(movie: Movie) {
    startActivity(Intent(this, MovieDetailsActivity::class.java).apply {
        putExtra(
            MovieDetailsActivity.BUNDLE_MOVIE_ID,
            movie.id
        )
    })
}