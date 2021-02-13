package com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels

import com.google.gson.annotations.SerializedName

data class RepositoryWorkspaceModel(
    @SerializedName("uuid")
    val workspaceId: String
)