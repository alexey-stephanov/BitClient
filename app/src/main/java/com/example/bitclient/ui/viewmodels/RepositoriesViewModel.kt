package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.RepositoriesDatabase
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.pagination.DataRetrieving
import com.example.bitclient.data.pagination.PagingRemoteMediator
import com.example.bitclient.data.pagination.RepositoriesRemoteMediator
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class RepositoriesViewModel(
    private val repository: RepositoriesRepository,
    database: RepositoriesDatabase
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
        RepositoriesRemoteMediator(repositoriesDao) { page -> retrieveData(page) }

    override fun getPagingSource(): PagingSource<Int, RepositoryDbModel> =
        repositoriesDao.getAll()
}