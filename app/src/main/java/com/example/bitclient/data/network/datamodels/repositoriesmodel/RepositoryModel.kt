package com.example.bitclient.data.network.datamodels.repositoriesmodel

import com.example.bitclient.data.network.datamodels.ValuesModel
import com.google.gson.annotations.SerializedName

data class RepositoryModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("uuid")
    val repositoryId: String,
    @SerializedName("links")
    val links: RepositoryLinksModel,
    @SerializedName("is_private")
    val isPrivate: Boolean
): ValuesModel()