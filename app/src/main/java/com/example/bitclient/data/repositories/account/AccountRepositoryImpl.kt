package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.network.api.RequestsApi
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.datamodels.accountmodel.networkmodels.toAccountDbModel
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.di.RequestQualifier
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    @RequestQualifier private val service: RequestsApi,
    private val accountDao: AccountDao
) : AccountRepository {

    override suspend fun retrieveAccountInfo() = flow {
        val dataFromDb: AccountDbModel? = accountDao.getActiveAccount()
        if(dataFromDb != null) {
            Timber.e("From db")
            emit(dataFromDb)
        }

        val dataFromNetwork = service.getUserInfo().toAccountDbModel()
        accountDao.insert(dataFromNetwork)
        Timber.e("From network")
        emit(dataFromNetwork)
    }

    override suspend fun updateAccountInfo(): AccountDbModel =
        service.getUserInfo().toAccountDbModel()
}