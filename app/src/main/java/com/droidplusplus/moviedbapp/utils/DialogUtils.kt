package com.droidplusplus.moviedbapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.droidplusplus.moviedbapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * show loading dialog
 */
var loadingDialog: Dialog? = null

fun Context?.showLoadingDialog(
    cancelable: Boolean = false,
    canceledOnTouchOutside: Boolean = false
): AlertDialog? {
    val context = this ?: return null
    return MaterialAlertDialogBuilder(context).apply {
        setView(R.layout.loading_dialog_layout)
    }.create().apply {
        setCancelable(cancelable)
        setCanceledOnTouchOutside(canceledOnTouchOutside)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    this@apply.dismiss()
                    if (loadingDialog === this@apply) {
                        loadingDialog = null
                    }
                }
            })
        }
        loadingDialog = this
        show()
    }
}

fun dismissLLoadingDialog() {
    if (loadingDialog?.isShowing == true) {
        loadingDialog?.dismiss()
    }
}

/**
 * show alert dialog
 */
var showingDialog: Dialog? = null

fun Context?.showDialog(
    title: String? = null, message: String? = null,
    textPositive: String? = null, positiveListener: (() -> Unit)? = null,
    textNegative: String? = null, negativeListener: (() -> Unit)? = null,
    cancelable: Boolean = false, canceledOnTouchOutside: Boolean = true
): AlertDialog? {
    val context = this ?: return null
    return MaterialAlertDialogBuilder(context).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(textPositive) { dialog, which ->
            positiveListener?.invoke()
        }
        setNegativeButton(textNegative) { dialog, which ->
            negativeListener?.invoke()
        }
        setCancelable(cancelable)
    }.create().apply {
        setCanceledOnTouchOutside(canceledOnTouchOutside)
        if (showingDialog?.isShowing == true) {
            showingDialog?.dismiss()
        }
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    this@apply.dismiss()
                    if (showingDialog === this@apply) {
                        showingDialog = null
                    }
                }
            })
        }
        setOnDismissListener {
            showingDialog = null
        }
        showingDialog = this
        show()
    }
}