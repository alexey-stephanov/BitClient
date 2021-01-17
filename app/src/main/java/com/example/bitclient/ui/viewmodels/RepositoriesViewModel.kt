package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspaceModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse
import com.example.bitclient.data.network.requests.UserDataRepository
import com.example.bitclient.data.pagination.PagingDataSource
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserWorkspacesLiveDataDelegate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userManager: UserManager
    //private val pagingDataSource: PagingDataSource
) : ViewModel(), UserWorkspacesLiveDataDelegate by userManager {

    lateinit var userWorkspacesResponse: WorkspacesResponse

    private val workspaceId = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            userWorkspacesResponse = userDataRepository.retrieveUserWorkspaces()
            liveWorkspaces.postValue(userWorkspacesResponse.workspaces)
        }
    }

    @ExperimentalCoroutinesApi
    val repositoriesFlow = workspaceId.flatMapLatest { workspaceId ->
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                PagingDataSource { page ->
                    userDataRepository.retrieveUserRepositories(workspaceId, page)
                }
            }).flow
    }.cachedIn(viewModelScope)

    fun switchWorkspaces(position: Int) {
        viewModelScope.launch {
            workspaceId.emit(userWorkspacesResponse.workspaces[position].workspaceId)
        }
    }

    fun getWorkspacesNames(): List<String> {
        val workspacesNames = mutableListOf<String>()
        liveWorkspaces.value?.forEach { workspaces ->
            workspacesNames.add(workspaces.workspaceName)
        }
        return workspacesNames
    }
}