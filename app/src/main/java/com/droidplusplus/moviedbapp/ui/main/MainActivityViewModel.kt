package com.droidplusplus.moviedbapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.data.remote.ApiParams
import com.droidplusplus.moviedbapp.data.repository.MovieRepository
import com.droidplusplus.moviedbapp.ui.base.BasePagedViewModel
import com.droidplusplus.moviedbapp.utils.searchInputData

class MainActivityViewModel(private val movieRepository: MovieRepository) :
    BasePagedViewModel<Movie>() {

    // temp list
    var mTempMovieList: List<Movie>? = emptyList()

    // search result liveData
    val searchResultList: LiveData<List<Movie>>
        get() = _searchResultList
    private val _searchResultList = MutableLiveData<List<Movie>>()

    override suspend fun loadData(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>?,
        loadParams: PageKeyedDataSource.LoadParams<Int>?
    ): List<Movie> {

        val apiParams = HashMap<String, String>()
        apiParams[ApiParams.PAGE] = (loadParams?.key ?: firstPage).toString()
        val result = movieRepository.getNowPlayingMovieAsync(apiParams)?.toList() ?: listOf()
        mTempMovieList = mTempMovieList?.plus(result)
        return result
    }

    override val pageSize: Int = 20

    fun searchMovie(inputQueryText: String) {
        val data = mTempMovieList?.filter {
            searchInputData(inputQueryText, it.title!!)
        }
        _searchResultList.value = data
    }

    override fun onCleared() {
        super.onCleared()
        mTempMovieList = null
    }
}