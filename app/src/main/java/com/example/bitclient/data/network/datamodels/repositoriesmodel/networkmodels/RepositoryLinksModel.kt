package com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels

import com.google.gson.annotations.SerializedName

data class RepositoryLinksModel(
    @SerializedName("avatar")
    val avatar: RepositoryAvatarModel
)