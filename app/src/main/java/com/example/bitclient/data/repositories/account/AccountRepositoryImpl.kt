package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.database.WorkspacesDao
import com.example.bitclient.data.network.api.RequestsApi
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.datamodels.accountmodel.networkmodels.toAccountDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel
import com.example.bitclient.di.RequestQualifier
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    @RequestQualifier private val service: RequestsApi,
    private val accountDao: AccountDao,
    private val workspacesDao: WorkspacesDao
) : AccountRepository {

    override suspend fun retrieveAccountInfo() = flow {
        var accountData: AccountDbModel? = accountDao.getActiveAccount()
        if (accountData != null) {
            Timber.e("From db")
            emit(accountData)
        }

        accountData = service.getUserInfo().toAccountDbModel()
        accountDao.insert(accountData)
        saveMainWorkspaceIntoDb(accountData)
        Timber.e("From network")
        emit(accountData)
    }

    override suspend fun updateAccountInfo(): AccountDbModel =
        service.getUserInfo().toAccountDbModel()

    private suspend fun saveMainWorkspaceIntoDb(accountDbModel: AccountDbModel) {
        workspacesDao.insert(WorkspaceDbModel(accountDbModel.workspaceId, accountDbModel.accountId, accountDbModel.displayName, 1))
    }
}