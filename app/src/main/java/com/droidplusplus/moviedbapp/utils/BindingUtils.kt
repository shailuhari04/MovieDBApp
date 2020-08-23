package com.droidplusplus.moviedbapp.utils

import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey

@BindingAdapter("onScrollListener")
fun RecyclerView.customScrollListener(listener: RecyclerView.OnScrollListener?) {
    if (listener != null) addOnScrollListener(listener)
}

@BindingAdapter(
    value = ["imageUrl", "placeholder", "errorDrawable", "requestListener", "centerCrop", "fitCenter", "circleCrop", "diskCacheStrategy", "signatureKey"],
    requireAll = false
)
fun ImageView.loadImage(
    imageUrl: String? = null,
    placeHolder: Drawable? = null,
    errorDrawable: Drawable? = null,
    requestListener: RequestListener<Drawable>? = null,
    centerCrop: Boolean = false,
    fitCenter: Boolean = false,
    circleCrop: Boolean = false,
    diskCacheStrategy: DiskCacheStrategy? = null,
    signatureKey: String? = null
) {
    if (imageUrl.isNullOrBlank()) {
        setImageDrawable(placeHolder)
        return
    }

    val requestOptions = RequestOptions().apply {

        // cache type: https://futurestud.io/tutorials/glide-how-to-choose-the-best-caching-preference
        // Glide 4.x: DiskCacheStrategy.RESOURCE Glide 3.x: DiskCacheStrategy.RESULT caches only the final image, after reducing the resolution (and possibly transformations) (default behavior of Glide 3.x)
        // Glide 4.x: DiskCacheStrategy.DATA, Glide 3.x: DiskCacheStrategy.SOURCE caches only the original full-resolution image
        // Glide 4.x only: DiskCacheStrategy.AUTOMATIC intelligently chooses a cache strategy based on the resource (default behavior of Glide 4.x)
        // Glide 3.x & 4.x: DiskCacheStrategy.ALL caches all versions of the image
        // Glide 3.x & 4.x: DiskCacheStrategy.NONE caches nothing
        diskCacheStrategy(diskCacheStrategy ?: DiskCacheStrategy.RESOURCE)

        placeholder(placeHolder)
        error(errorDrawable)

        // crop type
        if (centerCrop) centerCrop()
        if (fitCenter) fitCenter()
        if (circleCrop) circleCrop()

        if (!signatureKey.isNullOrBlank()) {
            signature(ObjectKey(signatureKey))
        }
    }

    Glide.with(context).load(imageUrl).apply {
        transition(DrawableTransitionOptions.withCrossFade())
        addListener(requestListener)
        apply(requestOptions)
    }.into(this)
}

@BindingAdapter("onSingleClick")
fun View.setSingleClick(execution: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0

        override fun onClick(p0: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < Constants.THRESHOLD_CLICK_TIME) {
                return
            }
            lastClickTime = SystemClock.elapsedRealtime()
            execution.invoke()
        }
    })
}