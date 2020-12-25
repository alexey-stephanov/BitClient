package com.example.bitclient.data.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.models.usermodel.UserModel
import com.example.bitclient.di.UserComponent
import javax.inject.Inject
import javax.inject.Singleton

class UserManagerImpl @Inject constructor(): UserManager {

    override val userModel: MutableLiveData<UserModel> by lazy {
        MutableLiveData<UserModel>()
    }

    override fun loginUser(userModel: UserModel) {
        this.userModel.postValue(userModel)
    }

    override fun logout() {
        userModel.value = null
    }

    //fun getUserInfo(): String = displayName
}

