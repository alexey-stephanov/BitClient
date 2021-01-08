package com.example.bitclient.data.network.networkmodels.repositoriesmodel

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse(
        @SerializedName("values")
        val values: List<RepositoryModel>,
        @SerializedName("next")
        val nextPage: String?
)