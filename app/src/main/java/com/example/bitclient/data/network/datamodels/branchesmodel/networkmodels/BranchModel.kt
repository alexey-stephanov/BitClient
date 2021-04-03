package com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels

import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.google.gson.annotations.SerializedName

data class BranchModel(
    @SerializedName("name")
    val branchName: String
)

fun BranchModel.toBranchDbModel(page: Int, ownerId: String) = BranchDbModel(
    branchId = branchName + ownerId,
    branchOwnerId = ownerId,
    branchName = branchName,
    page = page
)