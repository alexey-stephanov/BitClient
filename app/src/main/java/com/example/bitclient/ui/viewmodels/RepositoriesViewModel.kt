package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryDataMapper
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.data.pagination.DataRetrieving
import com.example.bitclient.data.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.account.AccountRepository
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
    private val accountRepository: AccountRepository,
    database: AccountDatabase,
    dataMapper: RepositoryDataMapper
) : PaginatedViewModel<RepositoryModel, RepositoryDbModel>(),
    DataRetrieving<RepositoryModel> {

    private val repositoriesDao = database.repositoriesDao()

    private val workspaceIdState = MutableStateFlow<String?>(null)

    @FlowPreview
    override val remoteMediator: PagingRemoteMediator<RepositoryModel, RepositoryDbModel> =
        PagingRemoteMediator(repositoriesDao, database, dataMapper) { page -> retrieveData(page) }

    init {
        if (NetworkStatus.isNetworkAvailable()) {
            viewModelScope.launch {
                val userInfo = accountRepository.retrieveUserInfoFromNetwork()
                accountRepository.saveUserInfoInDatabase(userInfo)
                workspaceIdState.emit(userInfo.workspaceId)
            }
        }
    }

    @FlowPreview
    override suspend fun retrieveData(page: Int): PaginatedResponse<RepositoryModel> {
        return workspaceIdState.filter { it != null }.map { workspaceId ->
            repositoriesRepository.retrieveUserRepositories(workspaceId!!, page)
        }.first()
    }

    override fun getPagingSource(): PagingSource<Int, RepositoryDbModel> =
        repositoriesDao.getAll()
}