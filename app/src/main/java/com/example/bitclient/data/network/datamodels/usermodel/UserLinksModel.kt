package com.example.bitclient.data.network.datamodels.usermodel

import com.google.gson.annotations.SerializedName

data class UserLinksModel(
        @SerializedName("avatar")
        val avatar: UserAvatarModel
)