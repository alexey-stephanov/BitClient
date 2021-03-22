package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import androidx.room.withTransaction
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

class UserManagerImpl @Inject constructor(
    private val storage: Storage,
    private val database: AccountDatabase
) : UserManager {

    override val liveAccountModel: MutableLiveData<AccountDbModel> = MutableLiveData()

    override fun loginUser(accountDbModel: AccountDbModel) {
        liveAccountModel.postValue(accountDbModel)
    }

    override suspend fun logout() {
        storage.clearStorage()
        liveAccountModel.value = null
    }
}

