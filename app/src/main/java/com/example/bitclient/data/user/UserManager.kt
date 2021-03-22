package com.example.bitclient.data.user

import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel

interface UserManager : UserAccountLiveDataDelegate {
    fun loginUser(accountDbModel: AccountDbModel)
    suspend fun logout()
}