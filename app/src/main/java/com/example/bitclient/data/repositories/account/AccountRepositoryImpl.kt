package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.datamodels.accountmodel.networkmodels.toAccountDbModel
import com.example.bitclient.data.network.api.RequestsApi
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    @RequestQualifier private val service: RequestsApi,
    private val userManager: UserManager,
    private val accountDao: AccountDao
) : AccountRepository {

    override suspend fun retrieveAccountInfo() = flow {
        //emit(accountDao.getActiveAccount(true))
        val accountInfo = service.getUserInfo().toAccountDbModel()
        userManager.loginUser(accountInfo)
        emit(accountInfo)
    }
}