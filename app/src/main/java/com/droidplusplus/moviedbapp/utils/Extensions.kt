package com.droidplusplus.moviedbapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import com.droidplusplus.moviedbapp.BuildConfig

/**
 * Below Extension Function for Logs.
 */
fun Any.showVLog(log: String) {
    if (BuildConfig.DEBUG) Log.v(this::class.java.simpleName, log)
}

fun Any.showELog(log: String) {
    if (BuildConfig.DEBUG) Log.e(this::class.java.simpleName, log)
}

fun Any.showDLog(log: String) {
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, log)
}

fun Any.showILog(log: String) {
    if (BuildConfig.DEBUG) Log.i(this::class.java.simpleName, log)
}

fun Any.showWLog(log: String) {
    if (BuildConfig.DEBUG) Log.w(this::class.java.simpleName, log)
}

/**
 * Helper Extension Function to check the internet connection
 */
fun Context.isOnline(): Boolean {
    var result = false
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    //Check if Connectivity manager is not NUll
    connectivityManager?.let {
        //We check the type of the connection is available for Internet
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    else -> false
                }
            }
        } else {
            result = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false
        }
    }
    // Return result by default the value will be false
    return result
}


/**
 * Helper Extension Function to set the visibility (visible | invisible | gone of view)
 */
fun View.visible() {
    visibility = View.VISIBLE
    return
}

fun View.invisible() {
    visibility = View.INVISIBLE
    return
}

fun View.gone() {
    visibility = View.GONE
    return
}
