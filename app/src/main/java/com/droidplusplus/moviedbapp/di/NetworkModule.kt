package com.droidplusplus.moviedbapp.di

import com.droidplusplus.moviedbapp.data.remote.APIClient.createAppRetrofit
import com.droidplusplus.moviedbapp.data.remote.APIClient.createHeaderInterceptor
import com.droidplusplus.moviedbapp.data.remote.APIClient.createLoggingInterceptor
import com.droidplusplus.moviedbapp.data.remote.APIClient.createOkHttpCache
import com.droidplusplus.moviedbapp.data.remote.APIClient.createOkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { createOkHttpCache(get()) }
    single(named("logging")) { createLoggingInterceptor() }
    single(named("header")) { createHeaderInterceptor() }
    single { createOkHttpClient(get(named("logging")), get(named("header"))) }
    single { createAppRetrofit(get()) }
}