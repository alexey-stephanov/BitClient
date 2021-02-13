package com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels

import com.google.gson.annotations.SerializedName

data class BranchOwnerModel(
    @SerializedName("uuid")
    val branchOwnerId: String
)