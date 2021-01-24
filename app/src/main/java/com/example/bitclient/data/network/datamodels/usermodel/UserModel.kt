package com.example.bitclient.data.network.datamodels.usermodel

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
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