package com.droidplusplus.moviedbapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.data.model.Review
import com.droidplusplus.moviedbapp.data.model.response.Cast
import com.droidplusplus.moviedbapp.data.remote.ApiParams
import com.droidplusplus.moviedbapp.data.repository.MovieRepository
import com.droidplusplus.moviedbapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    val movie: LiveData<Movie> get() = _movie
    private val _movie = MutableLiveData<Movie>()
    val noSummary = MutableLiveData(false)

    val castList: LiveData<ArrayList<Cast>> get() = _castList
    private val _castList = MutableLiveData<ArrayList<Cast>>()
    val noCastData = MutableLiveData(false)

    val reviewList: LiveData<ArrayList<Review>> get() = _reviewList
    private val _reviewList = MutableLiveData<ArrayList<Review>>()
    val noReviewData = MutableLiveData(false)

    val similarMovieList: LiveData<ArrayList<Movie>> get() = _similarMovieList
    private val _similarMovieList = MutableLiveData<ArrayList<Movie>>()
    val noSimilarMovieData = MutableLiveData<Boolean>(false)

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            try {
                val movieData = movieRepository.getMovieAsync(id)
                withContext(Dispatchers.Main) {
                    movieData?.overview.takeIf { it.isNullOrBlank() }?.let {
                        noSummary.value = true
                    } ?: run { noSummary.value = false }
                    _movie.postValue(movieData)
                }
            } catch (e: Exception) {
                onError(e)
                noSummary.value = true
            }
        }
    }

    fun getMovieCredits(id: String) {
        viewModelScope.launch {
            try {
                val creditsResponse = movieRepository.getMovieCreditsAsync(id)
                withContext(Dispatchers.Main) {
                    creditsResponse.cast.takeIf { it.isNullOrEmpty() }?.let {
                        noCastData.value = true
                    } ?: run { noCastData.value = false }
                    _castList.postValue(creditsResponse.cast)
                }
            } catch (e: Exception) {
                onError(e)
                noCastData.value = true
            }
        }
    }

    fun getMovieReviews(id: String) {
        viewModelScope.launch {
            try {
                val reviews = movieRepository.getMovieReviewsAsync(id)
                withContext(Dispatchers.Main) {
                    reviews.takeIf { it.isNullOrEmpty() }?.let {
                        noReviewData.value = true
                    } ?: run { noReviewData.value = false }
                    _reviewList.postValue(reviews as ArrayList<Review>?)
                }
            } catch (e: Exception) {
                onError(e)
                noReviewData.value = true
            }
        }
    }

    fun getSimilarMovie(id: String) {
        viewModelScope.launch {
            try {
                val apiParams = HashMap<String, String>()
                apiParams[ApiParams.PAGE] = 20.toString()
                val similarMovies = movieRepository.getMovieSimilarAsync(id, apiParams)
                withContext(Dispatchers.Main) {
                    similarMovies.takeIf { it.isNullOrEmpty() }?.let {
                        noSimilarMovieData.value = true
                    } ?: run { noSimilarMovieData.value = false }
                    _similarMovieList.postValue(similarMovies as ArrayList<Movie>?)
                }
            } catch (e: Exception) {
                onError(e)
                noSimilarMovieData.value = true
            }
        }
    }
}