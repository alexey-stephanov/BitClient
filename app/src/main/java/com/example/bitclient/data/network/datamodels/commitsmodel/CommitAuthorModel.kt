package com.example.bitclient.data.network.datamodels.commitsmodel

import com.google.gson.annotations.SerializedName

data class CommitAuthorModel(
    @SerializedName("raw")
    val author: String
)