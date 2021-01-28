package com.example.bitclient.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitclient.ui.recyclerview.OnItemClickListener
import com.example.bitclient.ui.recyclerview.PaginatedListAdapter
import com.example.bitclient.ui.viewmodels.PaginatedViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class PaginatedFragment<DataModel : Any> : Fragment() {

    protected abstract val viewModel: PaginatedViewModel<DataModel>

    protected abstract val paginatedListAdapter: PaginatedListAdapter<DataModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutResId(), container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
        subscribeOnPaginatedData()
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    private fun subscribeOnPaginatedData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.dataFlow.collect { pagingData ->
                paginatedListAdapter.submitData(pagingData)
            }
        }
    }

    abstract fun getRecyclerView(): RecyclerView

    private fun setupRecyclerView() {
        getRecyclerView().apply {
            setHasFixedSize(true)
            adapter = paginatedListAdapter
        }
    }
}