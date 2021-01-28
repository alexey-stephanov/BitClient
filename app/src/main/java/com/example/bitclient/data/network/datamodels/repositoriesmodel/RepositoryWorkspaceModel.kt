package com.example.bitclient.data.network.datamodels.repositoriesmodel

import com.google.gson.annotations.SerializedName

data class RepositoryWorkspaceModel(
    @SerializedName("slug")
    val workspaceId: String
)