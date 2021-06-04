package com.example.bitclient.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel
import com.example.bitclient.pagination.LoaderStateAdapter
import com.example.bitclient.ui.appbars.getAppBarsStateHandler
import com.example.bitclient.ui.recyclerview.adapters.PaginatedListAdapter
import com.example.bitclient.viewmodels.PaginatedViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class PaginatedFragment<DataModel : Any, DbDataModel : PaginatedDbModel> : Fragment() {

    @ExperimentalPagingApi
    abstract val viewModel: PaginatedViewModel<DataModel, DbDataModel>

    abstract val paginatedListAdapter: PaginatedListAdapter<DbDataModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutResId(), container, false)

    @LayoutRes
    abstract fun getLayoutResId(): Int

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        subscribeOnPaginatedData()
    }

    private fun setupRecyclerView() {
        paginatedListAdapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading) {
                with(bindShimmerFrameLayout()) {
                    visibility = View.VISIBLE
                    startShimmer()
                }
            } else if (it.refresh is LoadState.NotLoading) {
                with(bindShimmerFrameLayout()) {
                    stopShimmer()
                    visibility = View.GONE
                }
            }
        }
        bindRecyclerView().adapter =
            paginatedListAdapter.withLoadStateFooter(LoaderStateAdapter { paginatedListAdapter.retry() })
    }

    abstract fun bindShimmerFrameLayout(): ShimmerFrameLayout

    abstract fun bindRecyclerView(): RecyclerView

    override fun onStop() {
        super.onStop()
        getAppBarsStateHandler()?.show()
    }

    @ExperimentalPagingApi
    private fun subscribeOnPaginatedData() {
        lifecycleScope.launch {
            viewModel.dataFlow.collectLatest { pagingData ->
                paginatedListAdapter.submitData(pagingData)
            }
        }
    }
}