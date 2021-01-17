package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspaceModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class UserManagerImpl @Inject constructor() : UserManager {

    override val liveUserModel: MutableLiveData<UserModel> = MutableLiveData()

    override val liveWorkspaces: MutableLiveData<List<WorkspaceModel>> = MutableLiveData()

    private val workspaceIdFlow = MutableStateFlow("")
    override val workspaceIdState = workspaceIdFlow.asStateFlow()
    //override val liveWorkspacesIds: MutableLiveData<List<String>> = MutableLiveData()

    override fun loginUser(userModel: UserModel) {
        liveUserModel.postValue(userModel)
    }

    override fun logout() {
        liveUserModel.value = null
    }

    override fun pushId(id: String) {
        workspaceIdFlow.value = id
    }
}

