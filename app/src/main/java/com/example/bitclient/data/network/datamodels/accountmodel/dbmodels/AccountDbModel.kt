package com.example.bitclient.data.network.datamodels.accountmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class AccountDbModel(
    @PrimaryKey
    @ColumnInfo(name = "account_id")
    var accountId: String = "",
    @ColumnInfo(name = "display_name")
    var displayName: String = "",
    @ColumnInfo(name = "username")
    var username: String = "",
    @ColumnInfo(name = "avatar_link")
    var avatarLink: String = "",
    @ColumnInfo(name = "workspace_id")
    var workspaceId: String = ""
)