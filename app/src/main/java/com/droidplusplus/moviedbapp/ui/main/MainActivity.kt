package com.droidplusplus.moviedbapp.ui.main

import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.databinding.ActivityMainBinding
import com.droidplusplus.moviedbapp.ui.base.BaseListAdapter
import com.droidplusplus.moviedbapp.ui.base.BaseLoadMoreViewModel
import com.droidplusplus.moviedbapp.ui.details.MovieDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity :
    BaseLoadMoreMainActivity<ActivityMainBinding, BaseLoadMoreViewModel<Movie>, Movie>() {

    override val viewModel: MainActivityViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_main

    override val listAdapter: BaseListAdapter<Movie, out ViewDataBinding>
        get() = MovieListAdapter(
            itemClickListener = { navigateToMovieDetailsScreen(it) }
        )

    private fun navigateToMovieDetailsScreen(movie: Movie) {
        Intent(this, MovieDetailsActivity::class.java).apply {
            putExtra(MovieDetailsActivity.BUNDLE_MOVIE_ID, movie.id)
            startActivity(intent)
        }
    }
}