package com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels

import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class CommitModel(
    @SerializedName("author")
    val authorModel: CommitAuthorModel,
    @SerializedName("message")
    val message: String,
    @SerializedName("date")
    val date: Date
)

fun CommitModel.toCommitDbModel(page: Int) = CommitDbModel(
    authorName = authorModel.author,
    message = message,
    date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date),
    page = page
)