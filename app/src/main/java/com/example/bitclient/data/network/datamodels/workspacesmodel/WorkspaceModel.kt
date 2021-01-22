package com.example.bitclient.data.network.datamodels.workspacesmodel

import com.google.gson.annotations.SerializedName

data class WorkspaceModel(
    @SerializedName("name")
    val workspaceName: String,
    @SerializedName("uuid")
    val workspaceId: String
)