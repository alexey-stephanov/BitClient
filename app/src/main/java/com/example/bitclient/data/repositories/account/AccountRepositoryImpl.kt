package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.datamodels.accountmodel.networkmodels.toAccountDbModel
import com.example.bitclient.data.network.requests.RequestsApi
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    @RequestQualifier private val service: RequestsApi,
    private val accountDao: AccountDao
) : AccountRepository {

    override suspend fun retrieveAccountInfo() = flow {
        try {
            emit(accountDao.getActiveUser(true))
        } catch (e: NoSuchElementException) {
            emit(service.getUserInfo().toAccountDbModel())
        }
    }

    private suspend fun saveUserInfoIntoDatabase(accountDbModel: AccountDbModel) {
        accountDao.insert(accountDbModel)
    }
}