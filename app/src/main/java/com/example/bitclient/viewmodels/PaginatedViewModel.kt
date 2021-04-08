package com.example.bitclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.PagingDao
import com.example.bitclient.data.network.datamappers.NetworkToDbDataMapper
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse
import com.example.bitclient.pagination.PagingRemoteMediator
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
abstract class PaginatedViewModel<DataModel : Any, DbDataModel : PaginatedDbModel>(
    dao: PagingDao<DbDataModel>,
    database: AccountDatabase,
    dataMapper: NetworkToDbDataMapper<DataModel, DbDataModel>,
    ownerId: String,
    private val pagingSource: () -> PagingSource<Int, DbDataModel>,
    private val retrieveData: suspend (page: Int) -> PaginatedResponse<DataModel>
) : ViewModel() {

    private val mediator = PagingRemoteMediator(
        dao,
        database,
        dataMapper,
        ownerId
    ) { page ->
        retrieveData(page)
    }

    val dataFlow: Flow<PagingData<DbDataModel>> = Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            remoteMediator = mediator,
            pagingSourceFactory = { pagingSource() }
        ).flow.cachedIn(viewModelScope)

}