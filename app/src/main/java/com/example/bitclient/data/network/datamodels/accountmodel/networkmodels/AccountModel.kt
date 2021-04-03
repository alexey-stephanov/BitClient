package com.example.bitclient.data.network.datamodels.accountmodel.networkmodels

import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

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
    val workspaceId: String,
    @SerializedName("created_on")
    val createdOn: Date
)

fun AccountModel.toAccountDbModel() = AccountDbModel(
    accountId = accountId,
    displayName = displayName,
    username = username,
    avatarLink = links.avatar.href,
    workspaceId = workspaceId,
    createdOn = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(createdOn)
)