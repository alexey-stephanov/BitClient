package com.example.bitclient.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse
import com.example.bitclient.data.network.datamappers.RepositoryDataMapper
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.toWorkspaceDbModel
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.repositories.RepositoriesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class RepositoriesViewModel(
    private val repositoriesRepository: RepositoriesRepository,
    database: AccountDatabase,
    dataMapper: RepositoryDataMapper
) : PaginatedViewModel<RepositoryModel, RepositoryDbModel>() {

    private val repositoriesDao = database.repositoriesDao()

    private val workspaceIdState = MutableStateFlow<String?>(null)

    val workspacesLiveData = MutableLiveData<List<WorkspaceDbModel>>()

    @FlowPreview
    override val remoteMediator: PagingRemoteMediator<RepositoryModel, RepositoryDbModel> =
        PagingRemoteMediator(repositoriesDao, database, dataMapper) { page -> retrieveData(page) }

    init {
        if (NetworkStatus.isNetworkAvailable()) {
            viewModelScope.launch {
                loadAndSaveWorkspaces()
            }
        }
    }

    private suspend fun loadAndSaveWorkspaces() {
        val workspaces = repositoriesRepository.retrieveUserWorkspaces().values.map { workspace ->
            workspace.toWorkspaceDbModel(1)
        }
        workspacesLiveData.postValue(workspaces)
        workspaceIdState.emit(workspaces[0].workspaceId)
    }

    @FlowPreview
    suspend fun retrieveData(page: Int): PaginatedResponse<RepositoryModel> {
        return workspaceIdState.filter { it != null }.map { workspaceId ->
            repositoriesRepository.retrieveUserRepositories(workspaceId!!, page)
        }.first()
    }

    override fun getPagingSource(): PagingSource<Int, RepositoryDbModel> =
        repositoriesDao.getAll("")
}