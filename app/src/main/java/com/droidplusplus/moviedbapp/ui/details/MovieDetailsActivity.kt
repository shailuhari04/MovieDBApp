package com.droidplusplus.moviedbapp.ui.details

import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.databinding.ActivityMovieDetailsBinding
import com.droidplusplus.moviedbapp.ui.base.BaseActivity
import com.droidplusplus.moviedbapp.ui.main.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding, MainActivityViewModel>() {

    override val viewModel: MainActivityViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_movie_details

    companion object {
        const val BUNDLE_MOVIE_ID = "bundle_movie_id"
    }
}