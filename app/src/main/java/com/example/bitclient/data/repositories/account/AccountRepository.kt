package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.network.datamodels.usermodel.dbmodels.AccountDbModel

interface AccountRepository {
    suspend fun retrieveUserInfoFromNetwork(): AccountDbModel
    fun retrieveUserInfoFromDatabase(): AccountDbModel
}