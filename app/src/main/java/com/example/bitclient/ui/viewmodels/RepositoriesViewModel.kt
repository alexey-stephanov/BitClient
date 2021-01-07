package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.requests.RequestsDataRepository
import com.example.bitclient.data.pagination.RepositoryDataSource
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserWorkspacesLiveDataDelegate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val requestsDataRepository: RequestsDataRepository,
    private val userManager: UserManager
) : ViewModel(), UserWorkspacesLiveDataDelegate by userManager {

    fun getRepositoriesFlow(workspaceId: String): Flow<PagingData<RepositoryModel>> {
        return Pager(PagingConfig(pageSize = 10)) {
            RepositoryDataSource(requestsDataRepository, workspaceId)
        }.flow.cachedIn(viewModelScope)
    }

    fun loadUserWorkspaces() {
        viewModelScope.launch {
            val userWorkspaces = requestsDataRepository.retrieveUserWorkspaces()
            liveWorkspacesModel.postValue(userWorkspaces)
        }
    }
}