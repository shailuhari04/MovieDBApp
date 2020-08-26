package com.droidplusplus.moviedbapp.ui.main

import android.os.Bundle
import android.widget.SearchView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.droidplusplus.moviedbapp.ui.base.BaseActivity
import com.droidplusplus.moviedbapp.ui.base.BasePagedListAdapter
import com.droidplusplus.moviedbapp.ui.base.BasePagedViewModel
import com.droidplusplus.moviedbapp.utils.gone
import com.droidplusplus.moviedbapp.utils.navigateToMovieDetailsScreen
import com.droidplusplus.moviedbapp.utils.visible
import kotlinx.android.synthetic.main.activity_main.*

abstract class BasePagedMainActivity<ViewBinding : ViewDataBinding, ViewModel : BasePagedViewModel<Item>, Item> :
    BaseActivity<ViewBinding, ViewModel>() {

    abstract val pagedListAdapter: BasePagedListAdapter<Item, out ViewDataBinding>

    private val searchResultAdapter by lazy {
        SearchResultAdapter(itemClickListener = {
            navigateToMovieDetailsScreen(
                it
            )
        })
    }

    abstract val mViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
        setUpSearchView()
        setUpSearchResultData()
    }

    /**
     * setup
     */
    open fun setUpRecyclerView() {
        movieListRV?.adapter = pagedListAdapter
        viewModel.apply {
            itemList.observe(this@BasePagedMainActivity, Observer {
                pagedListAdapter.submitList(it)
            })
        }
    }

    private fun setUpSearchResultData() {
        searchResultRV?.adapter = searchResultAdapter
        mViewModel.searchResultList.observe(this@BasePagedMainActivity, {
            searchResultAdapter.submitList(it)
        })
    }

    private fun setUpSearchView() {
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                containerSearchLL.apply {
                    newText?.takeIf { it.isNotBlank() }?.let {
                        mViewModel.searchMovie(it)
                        visible()
                    } ?: run { gone() }
                }
                return true
            }
        })
    }


    override fun handleLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> {
                progressLoadingPB.visible()
            }
            false -> {
                progressLoadingPB.gone()
            }
        }
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.onActionViewCollapsed();
            containerSearchLL.gone()
        } else {
            super.onBackPressed();
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        movieListRV?.adapter = null
        viewBinding.unbind()
    }
}