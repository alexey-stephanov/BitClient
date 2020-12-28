package com.example.bitclient.data.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.models.usermodel.UserModel

interface UserManager : UserManagerDelegate {
    fun loginUser(userModel: UserModel)
    fun logout()
}

interface UserManagerDelegate {
    val liveUserModel: MutableLiveData<UserModel>
}