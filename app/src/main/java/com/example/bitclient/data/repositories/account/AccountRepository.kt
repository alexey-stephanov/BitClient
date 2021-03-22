package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun retrieveAccountInfo(): Flow<AccountDbModel>
}