package com.example.bitclient.data.network.datamodels.usermodel.networkmodels

import com.example.bitclient.data.network.datamodels.usermodel.dbmodels.AccountDbModel
import com.google.gson.annotations.SerializedName

data class AccountModel(
    @SerializedName("account_id")
    val accountId: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("links")
    val links: AccountLinksModel,
    @SerializedName("uuid")
    val workspaceId: String
)

fun AccountModel.toAccountDbModel() = AccountDbModel(
    accountId = accountId,
    displayName = displayName,
    username = username,
    avatarLink = links.avatar.href,
    workspaceId = workspaceId
)