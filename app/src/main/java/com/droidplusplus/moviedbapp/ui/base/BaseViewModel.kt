package com.droidplusplus.moviedbapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidplusplus.moviedbapp.data.remote.convertToBaseException
import com.droidplusplus.moviedbapp.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    // loading
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    // error message
    val errorMessage = SingleLiveEvent<String>()
    val noInternetConnectionEvent = SingleLiveEvent<Unit>()
    val connectTimeoutEvent = SingleLiveEvent<Unit>()
    val unknownErrorEvent = SingleLiveEvent<Unit>()

    /**
     * handle throwable when load fail
     */
    open suspend fun onError(throwable: Throwable) {
        withContext(Dispatchers.Main) {
            when (throwable) {
                // case no internet connection
                is UnknownHostException -> {
                    noInternetConnectionEvent.call()
                }
                is ConnectException -> {
                    noInternetConnectionEvent.call()
                }
                // case request time out
                is SocketTimeoutException -> {
                    connectTimeoutEvent.call()
                }
                else -> {
                    // convert throwable to base exception to get error information
                    val baseException = convertToBaseException(throwable)
                    when (baseException.httpCode) {
                        HttpURLConnection.HTTP_UNAUTHORIZED -> {
                            errorMessage.value = baseException.message
                        }
                        HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                            errorMessage.value = baseException.message
                        }
                        else -> {
                            unknownErrorEvent.call()
                        }
                    }
                }
            }
            hideLoading()
        }
    }

    fun showLoading() {
        isLoading.value = true
    }

    fun hideLoading() {
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }
}