package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.storage.Storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class UserManagerImpl @Inject constructor(private val storage: Storage) : UserManager {

    override val liveUserModel: MutableLiveData<UserModel> = MutableLiveData()

    override fun loginUser(userModel: UserModel) {
        liveUserModel.postValue(userModel)
    }

    override fun logout() {
        storage.clearStorage()
        liveUserModel.value = null
    }
}

