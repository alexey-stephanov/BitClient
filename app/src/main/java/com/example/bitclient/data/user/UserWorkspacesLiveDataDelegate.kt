package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspaceModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface UserWorkspacesLiveDataDelegate {
    val liveWorkspaces: MutableLiveData<List<WorkspaceModel>>
    val workspaceIdState: StateFlow<String>
}