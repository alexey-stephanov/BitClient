package com.example.bitclient.data.network.datamodels.branchesmodel

import com.google.gson.annotations.SerializedName

data class BranchModel(
    @SerializedName("name")
    val branchName: String
)