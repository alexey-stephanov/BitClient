package com.example.bitclient.data.network.datamodels.workspacesmodel

import com.google.gson.annotations.SerializedName

data class WorkspacesResponse(
    @SerializedName("values")
    val workspaces: List<WorkspaceModel>
)