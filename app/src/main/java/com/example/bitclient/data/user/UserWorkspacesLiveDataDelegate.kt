package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse

interface UserWorkspacesLiveDataDelegate {
    val liveWorkspacesModel: MutableLiveData<WorkspacesResponse>
}