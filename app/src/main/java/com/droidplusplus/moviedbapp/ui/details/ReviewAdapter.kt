package com.droidplusplus.moviedbapp.ui.details

import androidx.recyclerview.widget.DiffUtil
import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.data.model.Review
import com.droidplusplus.moviedbapp.databinding.ItemReviewRowBinding
import com.droidplusplus.moviedbapp.ui.base.BaseListAdapter
import com.droidplusplus.moviedbapp.utils.setSingleClick

class ReviewAdapter(
    val itemClickListener: (Review) -> Unit = {}
) : BaseListAdapter<Review, ItemReviewRowBinding>(object : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_review_row
    }

    override fun bindFirstTime(binding: ItemReviewRowBinding) {
        binding.apply {
            root.setSingleClick {
                item?.let {
                    itemClickListener.invoke(it)
                }
            }
        }
    }

}