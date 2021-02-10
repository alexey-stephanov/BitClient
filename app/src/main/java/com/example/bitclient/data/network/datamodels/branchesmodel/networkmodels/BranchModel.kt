package com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.google.gson.annotations.SerializedName

data class BranchModel(
    @SerializedName("target")
    val target: BranchTargetModel,
    @SerializedName("name")
    val branchName: String
)

fun BranchModel.toBranchDbModel(page: Int) = BranchDbModel(
    branchHash = target.branchHash,
    branchName = branchName,
    page = page
)