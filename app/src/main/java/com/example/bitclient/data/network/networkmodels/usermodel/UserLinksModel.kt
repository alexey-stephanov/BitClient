package com.example.bitclient.data.network.networkmodels.usermodel

import com.google.gson.annotations.SerializedName

data class UserLinksModel(
        @SerializedName("avatar")
        val avatar: UserAvatarModel
)