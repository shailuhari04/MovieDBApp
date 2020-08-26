package com.droidplusplus.moviedbapp

import android.app.Application
import com.droidplusplus.moviedbapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Koin initialization
        startKoin {
            androidContext(this@MyApplication)
            modules(appModules)
        }
    }

}