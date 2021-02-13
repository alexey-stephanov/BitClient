package com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels

import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class CommitModel(
    @SerializedName("hash")
    val commitHash: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("author")
    val authorModel: CommitAuthorModel,
    @SerializedName("date")
    val date: Date
)

fun CommitModel.toCommitDbModel(page: Int) = CommitDbModel(
    commitHash = commitHash,
    commitOwnerId = "master{c2f4ff5f-01f4-478c-bcd5-25050ace3ce2}",
    message = message,
    authorName = authorModel.author,
    date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date),
    page = page
)