package com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel
import com.google.gson.annotations.SerializedName

data class WorkspaceModel(
    @SerializedName("name")
    val workspaceName: String,
    @SerializedName("uuid")
    val workspaceId: String
)

fun WorkspaceModel.toWorkspaceDbModel(page: Int, ownerId: String) = WorkspaceDbModel(
    workspaceId = workspaceId,
    workspaceOwnerId = ownerId,
    workspaceName = workspaceName,
    page = page
)