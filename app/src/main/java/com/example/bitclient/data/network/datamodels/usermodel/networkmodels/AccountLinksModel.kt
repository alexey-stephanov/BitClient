package com.example.bitclient.data.network.datamodels.usermodel.networkmodels

import com.google.gson.annotations.SerializedName

data class AccountLinksModel(
    @SerializedName("avatar")
    val avatar: AccountAvatarModel
)