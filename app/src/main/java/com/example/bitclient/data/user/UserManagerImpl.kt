package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.models.usermodel.UserModel
import javax.inject.Inject

class UserManagerImpl @Inject constructor() : UserManager {

    override val liveUserModel: MutableLiveData<UserModel> = MutableLiveData()

    override fun loginUser(userModel: UserModel) {
        liveUserModel.postValue(userModel)
    }

    override fun logout() {
        liveUserModel.value = null
    }
}

