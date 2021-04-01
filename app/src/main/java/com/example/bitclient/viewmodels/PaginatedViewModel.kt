package com.example.bitclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel
import com.example.bitclient.pagination.PagingRemoteMediator
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
abstract class PaginatedViewModel<DataModel : Any, DbDataModel : PaginatedDbModel> : ViewModel() {

    abstract fun getPagingSource(): PagingSource<Int, DbDataModel>

    abstract val remoteMediator: PagingRemoteMediator<DataModel, DbDataModel>

    val dataFlow: Flow<PagingData<DbDataModel>> by lazy {
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { getPagingSource() }
        ).flow.cachedIn(viewModelScope)
    }
}