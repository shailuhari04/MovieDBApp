package com.droidplusplus.moviedbapp.ui.main

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.droidplusplus.moviedbapp.ui.base.BaseActivity
import com.droidplusplus.moviedbapp.ui.base.BaseListAdapter
import com.droidplusplus.moviedbapp.ui.base.BaseLoadMoreViewModel
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseLoadMoreMainActivity<ViewBinding : ViewDataBinding, ViewModel : BaseLoadMoreViewModel<Item>, Item> :
    BaseActivity<ViewBinding, ViewModel>() {

    abstract val listAdapter: BaseListAdapter<Item, out ViewDataBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoadMoreRefresh()
    }

    /**
     * setup default flow
     */
    open fun setupLoadMoreRefresh() {
//        refresh_layout?.setOnRefreshListener {
//            viewModel.doRefresh()
//        }

        movieListRV?.adapter = listAdapter
        viewModel.apply {
            itemList.observe(this@BaseLoadMoreMainActivity, Observer {
                listAdapter.submitList(it)
            })
            firstLoad()
        }
    }

    override fun handleLoading(isLoading: Boolean) {
        // use progress bar
    }

    override fun onDestroy() {
        super.onDestroy()
        movieListRV?.adapter = null
    }
}