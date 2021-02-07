package com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.google.gson.annotations.SerializedName

data class BranchModel(
    @SerializedName("name")
    val branchName: String
)

fun BranchModel.toBranchDbModel(page: Int) = BranchDbModel(
    branchName = branchName,
    page = page
)