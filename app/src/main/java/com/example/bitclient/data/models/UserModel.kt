package com.example.bitclient.data.models

import com.google.gson.annotations.SerializedName

data class UserModel(
        @SerializedName("username")
        val username: String,
        @SerializedName("nickname")
        val nickname: String,
        @SerializedName("account_status")
        val accountStatus: String,
        @SerializedName("website")
        val website: String
)