package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.pagination.RepositoryDataSource
import com.example.bitclient.data.network.requests.RequestsDataRepository
import com.example.bitclient.data.storage.Storage
import com.example.bitclient.data.user.UserLiveDataDelegate
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val userManager: UserManager,
    private val repositoryDataSource: RepositoryDataSource,
    private val requestsDataRepository: RequestsDataRepository
) : ViewModel(), UserLiveDataDelegate by userManager {

    val repositories: Flow<PagingData<RepositoryModel>> = Pager(PagingConfig(pageSize = 10)) {
        repositoryDataSource
    }.flow
        .cachedIn(viewModelScope)

    private suspend fun getRepositories(workspaceId: String, page: Int): RepositoriesResponse =
        requestsDataRepository.retrieveUserRepositories(workspaceId, page)
}