package com.droidplusplus.moviedbapp.ui.base

import androidx.lifecycle.MutableLiveData
import com.droidplusplus.moviedbapp.utils.Constants
import com.droidplusplus.moviedbapp.utils.EndlessRecyclerOnScrollListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseLoadMoreViewModel<Item>() : BaseViewModel() {

    // load more flag
    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }

    // current page
    private val currentPage = MutableLiveData<Int>().apply { value = getPreFirstPage() }

    // last page flag
    private val isLastPage = MutableLiveData<Boolean>().apply { value = false }

    // scroll listener for recycler view
    val onScrollListener = object : EndlessRecyclerOnScrollListener(getLoadMoreThreshold()) {
        override fun onLoadMore() {
            doLoadMore()
        }
    }

    // item list
    val itemList = MutableLiveData<ArrayList<Item>>()

    // empty list flag
    val isEmptyList = MutableLiveData<Boolean>().apply { value = false }

    /**
     * load data
     */
    abstract fun loadData(page: Int)

    /**
     * check first time load data
     */
    private fun isFirst() = currentPage.value == getPreFirstPage()
            && itemList.value?.isEmpty() ?: true

    /**
     * first load
     */
    fun firstLoad() {
        if (isFirst()) {
            showLoading()
            loadData(getFirstPage())
        }
    }

    /**
     * load More
     */
    fun doLoadMore() {
        if (isLoading.value == true
            || isLoadMore.value == true
            || isLastPage.value == true
        ) return
        isLoadMore.value = true
        loadMore()
    }

    /**
     * load next page
     */
    protected fun loadMore() {
        loadData(currentPage.value?.plus(1) ?: getFirstPage())
    }

    /**
     * override if first page is not 1
     */
    open fun getFirstPage() = Constants.DEFAULT_FIRST_PAGE

    private fun getPreFirstPage() = getFirstPage() - 1

    /**
     * override if need change number visible threshold
     */
    open fun getLoadMoreThreshold() = Constants.DEFAULT_NUM_VISIBLE_THRESHOLD

    /**
     * override if need change number item per page
     */
    open fun getNumberItemPerPage() = Constants.DEFAULT_ITEM_PER_PAGE

    /**
     * reset load more
     */
    fun resetLoadMore() {
        onScrollListener.resetOnLoadMore()
        isLastPage.value = false
    }

    /**
     * handle load success
     */
    suspend fun onLoadSuccess(page: Int, items: List<Item>?) {
        withContext(Dispatchers.Main) {
            // load success then update current page
            currentPage.value = page
            // case load first page then clear data from listItem
            if (currentPage.value == getFirstPage()) itemList.value?.clear()

            // add new data to listItem
            val newList = itemList.value ?: ArrayList()
            newList.addAll(items ?: listOf())
            itemList.value = newList

            // check last page
            isLastPage.value = items?.size ?: 0 < getNumberItemPerPage()

            // reset load
            hideLoading()
            isLoadMore.value = false

            // check empty list
            checkEmptyList()
        }
    }

    /**
     * handle load fail
     */
    override suspend fun onError(throwable: Throwable) {
        withContext(Dispatchers.Main) {
            super.onError(throwable)
            onScrollListener.isLoading = false

            // reset load
            isLoadMore.value = false

            // check empty list
            checkEmptyList()
        }
    }

    /**
     * check list is empty
     */
    private fun checkEmptyList() {
        isEmptyList.value = itemList.value?.isEmpty() ?: true
    }
}