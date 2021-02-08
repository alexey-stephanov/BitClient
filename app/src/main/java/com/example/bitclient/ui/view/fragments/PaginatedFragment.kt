package com.example.bitclient.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel
import com.example.bitclient.data.pagination.LoaderStateAdapter
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.viewmodels.PaginatedViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class PaginatedFragment<DataModel : Any, DbDataModel : PaginatedDbModel> : Fragment() {

    @ExperimentalPagingApi
    protected abstract val viewModel: PaginatedViewModel<DataModel, DbDataModel>

    protected abstract val paginatedListAdapter: PaginatedListAdapter<DbDataModel>

    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutResId(), container, false)


    @ExperimentalPagingApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        subscribeOnPaginatedData()
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    @ExperimentalPagingApi
    private fun subscribeOnPaginatedData() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.dataFlow.collectLatest { pagingData ->
                paginatedListAdapter.submitData(pagingData)
            }
        }
    }

    abstract fun getRecyclerView(): RecyclerView

    private fun setupRecyclerView() {
        getRecyclerView().apply {
            setHasFixedSize(true)
            adapter =
                paginatedListAdapter.withLoadStateFooter(LoaderStateAdapter { paginatedListAdapter.retry() })
        }
    }
}