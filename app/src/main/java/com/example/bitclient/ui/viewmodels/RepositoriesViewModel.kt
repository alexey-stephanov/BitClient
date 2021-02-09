package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.NetworkToDbDataMapper
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryDataMapper
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.pagination.DataRetrieving
import com.example.bitclient.data.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class RepositoriesViewModel(
    private val repository: RepositoriesRepository,
    database: AccountDatabase,
    dataMapper: RepositoryDataMapper
) : PaginatedViewModel<RepositoryModel, RepositoryDbModel>(),
    DataRetrieving<RepositoryModel> {

    private val repositoriesDao = database.repositoriesDao()

    init {
        viewModelScope.launch {
            val userWorkspaces = repository.retrieveUserWorkspaces()
            state.emit(userWorkspaces.workspaces[0].workspaceId)
        }
    }

    val state = MutableStateFlow<String?>(null)

    @FlowPreview
    override suspend fun retrieveData(page: Int): PaginatedResponse<RepositoryModel> {
        return state.filter { it != null }.map {
            repository.retrieveUserRepositories(it!!, page)
        }.first()
    }

    @FlowPreview
    override val remoteMediator: PagingRemoteMediator<RepositoryModel, RepositoryDbModel> =
        PagingRemoteMediator(repositoriesDao, dataMapper) { page -> retrieveData(page) }

    override fun getPagingSource(): PagingSource<Int, RepositoryDbModel> =
        repositoriesDao.getAll()
}