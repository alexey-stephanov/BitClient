package com.example.bitclient.data.user

import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel

interface UserManager : UserAccountLiveDataDelegate {
    suspend fun loginUser(accountDbModel: AccountDbModel)
    suspend fun logout(isClearDataNeeded: Boolean)
}