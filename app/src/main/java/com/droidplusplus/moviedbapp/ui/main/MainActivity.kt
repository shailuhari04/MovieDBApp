package com.droidplusplus.moviedbapp.ui.main

import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.databinding.ActivityMainBinding
import com.droidplusplus.moviedbapp.utils.navigateToMovieDetailsScreen
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity :
    BasePagedMainActivity<ActivityMainBinding, MainActivityViewModel, Movie>() {

    override val viewModel: MainActivityViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_main

    override val pagedListAdapter by lazy {
        PagedMovieAdapter(itemClickListener = {
            navigateToMovieDetailsScreen(
                it
            )
        })
    }

    override val mViewModel: MainActivityViewModel
        get() = viewModel
}
