package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel

interface AccountRepository {
    suspend fun retrieveUserInfoFromNetwork(): AccountDbModel
    suspend fun retrieveUserInfoFromDatabase(): AccountDbModel
    suspend fun saveUserInfoInDatabase(accountDbModel: AccountDbModel)
}