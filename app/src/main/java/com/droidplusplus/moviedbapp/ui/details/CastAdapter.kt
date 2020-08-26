package com.droidplusplus.moviedbapp.ui.details

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.data.model.response.Cast
import com.droidplusplus.moviedbapp.databinding.ItemCastRowBinding
import com.droidplusplus.moviedbapp.ui.base.BaseListAdapter
import com.droidplusplus.moviedbapp.utils.setSingleClick

class CastAdapter(
    val itemClickListener: ((ImageView, Cast) -> Unit)? = null
) : BaseListAdapter<Cast, ItemCastRowBinding>(object : DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_cast_row
    }

    override fun bindFirstTime(binding: ItemCastRowBinding) {
        binding.apply {
            root.setSingleClick {
                item?.let {
                    itemClickListener?.invoke(profileIV, it)
                }
            }
        }
    }

}