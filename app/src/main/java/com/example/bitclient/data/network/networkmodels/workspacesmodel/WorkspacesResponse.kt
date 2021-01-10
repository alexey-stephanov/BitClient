package com.example.bitclient.data.network.networkmodels.workspacesmodel

import com.google.gson.annotations.SerializedName

data class WorkspacesResponse(
    @SerializedName("values")
    val workspaces: List<WorkspaceModel>,
    @SerializedName("next")
    val nextPage: String?
)