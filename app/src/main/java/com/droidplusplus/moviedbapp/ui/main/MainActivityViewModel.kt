package com.droidplusplus.moviedbapp.ui.main

import androidx.lifecycle.viewModelScope
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.data.remote.ApiParams
import com.droidplusplus.moviedbapp.data.remote.ApiService
import com.droidplusplus.moviedbapp.ui.base.BaseLoadMoreViewModel
import kotlinx.coroutines.launch

class MainActivityViewModel(private val apiService: ApiService) : BaseLoadMoreViewModel<Movie>() {

    override fun loadData(page: Int) {
        val hashMap = HashMap<String, String>()
        hashMap[ApiParams.PAGE] = page.toString()

        viewModelScope.launch {
            try {
                onLoadSuccess(page, apiService.getNowPlayingMovieAsync(hashMap).results)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}