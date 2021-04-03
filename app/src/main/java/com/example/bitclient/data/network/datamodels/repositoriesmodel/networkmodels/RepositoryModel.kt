package com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.google.gson.annotations.SerializedName

data class RepositoryModel(
    @SerializedName("uuid")
    val repositoryId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("links")
    val links: RepositoryLinksModel,
    @SerializedName("is_private")
    val isPrivate: Boolean
)

fun RepositoryModel.toRepositoryDbModel(page: Int, ownerId: String) = RepositoryDbModel(
    repositoryId = repositoryId,
    repositoryOwnerId = ownerId,
    name = name,
    fullName = fullName,
    avatarLink = links.avatar.href,
    isPrivate = isPrivate,
    page = page
)