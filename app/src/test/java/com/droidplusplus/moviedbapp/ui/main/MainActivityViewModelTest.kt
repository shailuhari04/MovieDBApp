package com.droidplusplus.moviedbapp.ui.main

import com.droidplusplus.moviedbapp.base.BaseViewModelTest
import com.droidplusplus.moviedbapp.data.remote.ApiService
import com.droidplusplus.moviedbapp.data.repository.MovieRepository
import com.droidplusplus.moviedbapp.data.repository.MovieRepositoryImpl
import com.droidplusplus.moviedbapp.datasource.createMovieListResponse
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock

class MainActivityViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var apiService: ApiService

    private lateinit var movieRepository: MovieRepository

    override fun setup() {
        super.setup()
        apiService = mock(ApiService::class.java)
        movieRepository = MovieRepositoryImpl(apiService)
        viewModel = MainActivityViewModel(movieRepository)
    }

    @Test
    fun `search Movie Result Case1 Test`() {
        val fakeData = createMovieListResponse()

        viewModel.mTempMovieList = fakeData.results

        viewModel.searchMovie("Th")

        Assert.assertEquals(2, viewModel.searchResultList.value?.size)

        viewModel.searchMovie("B ")

        Assert.assertEquals(0, viewModel.searchResultList.value?.size)

        viewModel.searchMovie("Rain in ")

        Assert.assertEquals("Rain in the Garden", viewModel.searchResultList.value?.first()?.title)
    }

    @Test
    fun `search Movie Result Case2 Test`() {
        val fakeData = createMovieListResponse()

        viewModel.mTempMovieList = fakeData.results

        viewModel.searchMovie("in the Garden Rain")

        Assert.assertEquals(3, viewModel.searchResultList.value?.size)

        viewModel.searchMovie("The")

        Assert.assertEquals(2, viewModel.searchResultList.value?.size)
    }
}