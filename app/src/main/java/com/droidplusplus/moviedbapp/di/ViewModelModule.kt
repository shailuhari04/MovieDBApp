package com.droidplusplus.moviedbapp.di

import com.droidplusplus.moviedbapp.ui.details.MovieDetailViewModel
import com.droidplusplus.moviedbapp.ui.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}