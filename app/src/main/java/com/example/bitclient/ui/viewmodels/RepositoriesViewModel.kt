package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse
import com.example.bitclient.data.network.requests.UserDataRepository
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserWorkspacesLiveDataDelegate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userManager: UserManager
) : PaginatedViewModel<RepositoryModel>({
    userDataRepository.retrieveUserRepositories("", it)
}), UserWorkspacesLiveDataDelegate by userManager {

    private val workspaceId = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            val userWorkspacesResponse = userDataRepository.retrieveUserWorkspaces()
            liveWorkspaces.postValue(userWorkspacesResponse.workspaces)
        }
    }
}