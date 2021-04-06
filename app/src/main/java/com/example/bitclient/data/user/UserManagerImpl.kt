package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

class UserManagerImpl @Inject constructor(
    private val storage: Storage,
    private val accountDao: AccountDao
) : UserManager {

    override val liveAccountModel: MutableLiveData<AccountDbModel> = MutableLiveData()

    override suspend fun loginUser(accountDbModel: AccountDbModel) {
        liveAccountModel.postValue(accountDbModel)
        accountDao.insert(accountDbModel)
    }

    override suspend fun logout(isClearDataNeeded: Boolean) {
        storage.clearStorage()
        accountDao.inactiveAccount()
        liveAccountModel.value = null
        if (isClearDataNeeded)
            accountDao.clearAll()
    }
}

