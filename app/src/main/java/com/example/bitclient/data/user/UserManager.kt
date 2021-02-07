package com.example.bitclient.data.user

import com.example.bitclient.data.network.datamodels.usermodel.dbmodels.AccountDbModel

interface UserManager : UserInfoLiveDataDelegate {
    fun loginUser(accountDbModel: AccountDbModel)
    suspend fun logout()
}