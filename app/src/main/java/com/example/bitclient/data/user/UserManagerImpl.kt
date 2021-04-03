package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import androidx.room.withTransaction
import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

class UserManagerImpl @Inject constructor(
    private val storage: Storage,
    private val database: AccountDatabase,
    private val accountDao: AccountDao
) : UserManager {

    override val liveAccountModel: MutableLiveData<AccountDbModel> = MutableLiveData()

    override suspend fun loginUser(accountDbModel: AccountDbModel) {
        liveAccountModel.postValue(accountDbModel)
//        database.withTransaction {
//            with(database) {
//                accountDao().insert(accountDbModel)
//            }
//        }
        accountDao.insert(accountDbModel)
    }

    override suspend fun logout(isClearDataNeeded: Boolean) {
        storage.clearStorage()
        liveAccountModel.value = null
        //        database.withTransaction {
//                database.accountDao().clearAll()
//            }
//        }
        if(isClearDataNeeded)
            accountDao.clearAll()
    }
}

