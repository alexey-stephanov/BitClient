package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.bitclient.data.network.networkmodels.PaginatedResponse
import com.example.bitclient.data.pagination.PagingDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi

abstract class PaginatedViewModel<T : Any>(private val retrieveData: suspend (page: Int) -> PaginatedResponse<T>) : ViewModel() {

    @ExperimentalCoroutinesApi
    val paginatedFlow = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {
            PagingDataSource { page ->
                retrieveData(page)
            }
        }).flow.cachedIn(viewModelScope)
}