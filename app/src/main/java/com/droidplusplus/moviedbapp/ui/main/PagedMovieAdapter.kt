package com.droidplusplus.moviedbapp.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.droidplusplus.moviedbapp.R
import com.droidplusplus.moviedbapp.data.model.Movie
import com.droidplusplus.moviedbapp.databinding.ItemMovieRowBinding
import com.droidplusplus.moviedbapp.ui.base.BasePagedListAdapter
import com.droidplusplus.moviedbapp.utils.setSingleClick

class PagedMovieAdapter(
    val itemClickListener: (Movie) -> Unit = {}
) : BasePagedListAdapter<Movie, ItemMovieRowBinding>(object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_movie_row
    }

    override fun bindFirstTime(binding: ItemMovieRowBinding) {
        binding.apply {
            bookBtn.setSingleClick {
                item?.apply(itemClickListener)
            }
        }
    }

}