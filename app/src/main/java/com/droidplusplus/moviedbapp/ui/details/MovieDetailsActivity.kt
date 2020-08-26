package com.droidplusplus.moviedbapp.ui.details

import android.os.Bundle
import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.databinding.ActivityMovieDetailsBinding
import com.droidplusplus.moviedbapp.ui.base.BaseActivity
import com.droidplusplus.moviedbapp.utils.navigateToMovieDetailsScreen
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity :
    BaseActivity<ActivityMovieDetailsBinding, MovieDetailViewModel>() {

    override val viewModel: MovieDetailViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_movie_details

    companion object {
        const val BUNDLE_MOVIE_ID = "bundle_movie_id"
    }

    private val castListAdapter by lazy { CastAdapter() }

    private val reviewListAdapter by lazy { ReviewAdapter() }

    private val similarMovieListAdapter by lazy {
        SimilarMovieAdapter(itemClickListener = {
            navigateToMovieDetailsScreen(
                it
            )
        })
    }

    private val movieId: String? by lazy { intent?.getStringExtra(BUNDLE_MOVIE_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
        setUpToolbar()
        setUpMovieDetails()
        setupCastListData()
        setupSimilarMovieListData()
        setupReviewListData()
    }

    private fun setUpToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadData() {
        movieId?.takeIf { it.isNotBlank() }?.let {
            viewModel.getMovieDetails(it)
            viewModel.getMovieCredits(it)
            viewModel.getSimilarMovie(it)
            viewModel.getMovieReviews(it)
        }
    }

    private fun setUpMovieDetails() {
        viewModel.movie.observe(this@MovieDetailsActivity, {
            viewBinding.movie = it
        })
    }

    private fun setupReviewListData() {
        reviewListRV?.adapter = reviewListAdapter
        viewModel.reviewList.observe(this@MovieDetailsActivity, {
            reviewListAdapter.submitList(it)
        })
    }

    private fun setupCastListData() {
        castListRV?.adapter = castListAdapter
        viewModel.castList.observe(this@MovieDetailsActivity, {
            castListAdapter.submitList(it)
        })
    }

    private fun setupSimilarMovieListData() {
        similarMovieListRV?.adapter = similarMovieListAdapter
        viewModel.similarMovieList.observe(this@MovieDetailsActivity, {
            similarMovieListAdapter.submitList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        castListRV?.adapter = null
        similarMovieListRV?.adapter = null
        reviewListRV?.adapter = null
        viewBinding.unbind()
    }
}