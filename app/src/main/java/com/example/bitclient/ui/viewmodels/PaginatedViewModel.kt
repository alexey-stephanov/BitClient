package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bitclient.data.network.datamodels.PaginatedResponse
import com.example.bitclient.data.pagination.PagingDataSource
import kotlinx.coroutines.flow.Flow

abstract class PaginatedViewModel<DataModel : Any> : ViewModel() {

    abstract suspend fun retrieveData(page: Int) : PaginatedResponse<DataModel>

    val dataFlow = getPagingDataFlow()

    private fun getPagingDataFlow(): Flow<PagingData<DataModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                PagingDataSource { page ->
                    retrieveData(page)
                }
            }).flow.cachedIn(viewModelScope)
    }
}