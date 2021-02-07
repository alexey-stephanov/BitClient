package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import androidx.room.withTransaction
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.RepositoriesDatabase
import com.example.bitclient.data.di.user.AccountDbQualifier
import com.example.bitclient.data.di.user.RepositoriesDbQualifier
import com.example.bitclient.data.network.datamodels.usermodel.dbmodels.AccountDbModel
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

class UserManagerImpl @Inject constructor(
    private val storage: Storage,
    @AccountDbQualifier private val accountDatabase: AccountDatabase,
    @RepositoriesDbQualifier private val repositoriesDatabase: RepositoriesDatabase
) : UserManager {

    override val liveAccountModel: MutableLiveData<AccountDbModel> = MutableLiveData()

    override fun loginUser(accountDbModel: AccountDbModel) {
        liveAccountModel.postValue(accountDbModel)
    }

    override suspend fun logout() {
        storage.clearStorage()
        liveAccountModel.value = null
        accountDatabase.withTransaction {
            accountDatabase.accountDao().clearAll()
        }
        repositoriesDatabase.withTransaction {
            with(repositoriesDatabase) {
                repositoriesDao().clearAll()
                branchesDao().clearAll()
                commitsDao().clearAll()
            }
        }
    }
}

