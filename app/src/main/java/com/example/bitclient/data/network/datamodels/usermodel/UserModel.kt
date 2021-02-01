package com.example.bitclient.data.network.datamodels.usermodel

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserModel(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        @SerializedName("account_id")
        var accountId: String = "",
        @SerializedName("display_name")
        var displayName: String = "",
        @SerializedName("username")
        var username: String = "",
        @SerializedName("links")
        @Ignore
        var links: UserLinksModel = UserLinksModel(UserAvatarModel("")),
        @SerializedName("uuid")
        var workspaceId: String = ""
)