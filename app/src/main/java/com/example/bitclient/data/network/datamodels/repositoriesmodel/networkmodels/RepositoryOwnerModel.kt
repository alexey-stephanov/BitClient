package com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels

import com.google.gson.annotations.SerializedName

data class RepositoryOwnerModel(
    @SerializedName("account_id")
    val repositoryOwnerId: String
)