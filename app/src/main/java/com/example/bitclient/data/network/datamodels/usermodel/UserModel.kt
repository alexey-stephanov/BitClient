package com.example.bitclient.data.network.datamodels.usermodel

import com.google.gson.annotations.SerializedName

data class UserModel(
        @SerializedName("display_name")
        val displayName: String,
        @SerializedName("username")
        val username: String,
        @SerializedName("links")
        val links: UserLinksModel,
        @SerializedName("uuid")
        val workspaceId: String
)