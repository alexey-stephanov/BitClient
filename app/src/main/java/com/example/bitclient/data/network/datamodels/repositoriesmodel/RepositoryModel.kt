package com.example.bitclient.data.network.datamodels.repositoriesmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories")
data class RepositoryModel(
    @PrimaryKey
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