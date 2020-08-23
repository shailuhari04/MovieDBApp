package com.droidplusplus.moviedbapp.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.droidplusplus.moviedbapp.BR
import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.utils.dismissLLoadingDialog
import com.droidplusplus.moviedbapp.utils.showDialog
import com.droidplusplus.moviedbapp.utils.showLoadingDialog

abstract class BaseActivity<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> :
    AppCompatActivity() {

    protected lateinit var viewBinding: ViewBinding

    protected abstract val viewModel: ViewModel

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::viewBinding.isInitialized.not()) {
            viewBinding = DataBindingUtil.setContentView(this, layoutId)
            viewBinding.setVariable(BR.viewModel, viewModel)
        }

        observeErrorEvent()
    }

    protected fun observeErrorEvent() {
        viewModel.apply {
            isLoading.observe(this@BaseActivity, Observer {
                handleLoading(it == true)
            })
            errorMessage.observe(this@BaseActivity, Observer {
                handleErrorMessage(it)
            })
            noInternetConnectionEvent.observe(this@BaseActivity, Observer {
                handleErrorMessage(getString(R.string.no_internet_connection))
            })
            connectTimeoutEvent.observe(this@BaseActivity, Observer {
                handleErrorMessage(getString(R.string.connect_timeout))
            })
            unknownErrorEvent.observe(this@BaseActivity, Observer {
                handleErrorMessage(getString(R.string.unknown_error))
            })
        }
    }

    /**
     * override this if not use loading dialog (ex. progress)
     */
    open fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoading() else dismissLLoadingDialog()
    }

    fun showLoading() {
        showLoadingDialog()
    }

    fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return

        dismissLLoadingDialog()

        showDialog(
            message = message,
            textPositive = getString(R.string.ok)
        )
    }
}