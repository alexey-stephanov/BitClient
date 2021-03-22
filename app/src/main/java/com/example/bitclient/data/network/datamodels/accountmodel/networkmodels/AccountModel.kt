package com.example.bitclient.data.network.datamodels.accountmodel.networkmodels

import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.google.gson.annotations.SerializedName

data class AccountModel(
    @SerializedName("account_id")
    val accountId: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("links")
    val links: AccountLinksModel
)

fun AccountModel.toAccountDbModel() = AccountDbModel(
    accountId = accountId,
    displayName = displayName,
    username = username,
    avatarLink = links.avatar.href,
    isActive = true
)