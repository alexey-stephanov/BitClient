package com.example.bitclient.data.network.datamodels.repositoriesmodel

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories")
data class RepositoryModel(
    @PrimaryKey
    @SerializedName("uuid")
    var repositoryId: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("full_name")
    var fullName: String = "",
    @SerializedName("links")
    @Ignore
    var links: RepositoryLinksModel = RepositoryLinksModel(RepositoryAvatarModel("")),
    @SerializedName("workspace")
    @Ignore
    var workspace: RepositoryWorkspaceModel = RepositoryWorkspaceModel(""),
    @SerializedName("is_private")
    var isPrivate: Boolean = false
)