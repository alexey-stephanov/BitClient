package com.example.bitclient.data.network.networkmodels.repositoriesmodel

import com.google.gson.annotations.SerializedName

data class RepositoryLinksModel(
    @SerializedName("avatar")
    val avatar: RepositoryAvatarModel
)