package com.droidplusplus.moviedbapp.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.databinding.ItemSearchRowBinding
import com.droidplusplus.moviedbapp.ui.base.BaseListAdapter
import com.droidplusplus.moviedbapp.utils.setSingleClick

class SearchResultAdapter(
    val itemClickListener: (Movie) -> Unit = {}
) : BaseListAdapter<Movie, ItemSearchRowBinding>(object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_search_row
    }

    override fun bindFirstTime(binding: ItemSearchRowBinding) {
        binding.apply {
            root.setSingleClick {
                item?.let {
                    itemClickListener.invoke(it)
                }
            }
        }
    }

}