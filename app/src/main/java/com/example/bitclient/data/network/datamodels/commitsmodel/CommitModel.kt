package com.example.bitclient.data.network.datamodels.commitsmodel

import com.google.gson.annotations.SerializedName
import java.util.*

data class CommitModel(
    @SerializedName("author")
    val authorModel: CommitAuthorModel,
    @SerializedName("message")
    val massage: String,
    @SerializedName("date")
    val date: Date
)