package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.bitclient.data.network.requests.UserDataRepository
import com.example.bitclient.data.pagination.PagingDataSource
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserWorkspacesLiveDataDelegate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userManager: UserManager
) : ViewModel(), UserWorkspacesLiveDataDelegate by userManager {

    private val workspaceId: Flow<String> = MutableStateFlow("")

    init {
        viewModelScope.launch {
            val userWorkspaces = userDataRepository.retrieveUserWorkspaces()
            liveWorkspacesModel.postValue(userWorkspaces)
        }
    }

    val repositoriesFlow = Pager(PagingConfig(pageSize = 10)) {
            PagingDataSource(userDataRepository, workspaceId)
        }.flow.cachedIn(viewModelScope)
}