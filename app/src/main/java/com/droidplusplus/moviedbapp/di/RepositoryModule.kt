package com.droidplusplus.moviedbapp.di

import com.droidplusplus.moviedbapp.data.repository.MovieRepository
import com.droidplusplus.moviedbapp.data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}