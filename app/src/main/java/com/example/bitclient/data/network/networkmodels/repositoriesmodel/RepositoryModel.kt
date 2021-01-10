package com.example.bitclient.data.network.networkmodels.repositoriesmodel

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
)