package com.example.bitclient.data.user

import androidx.lifecycle.LiveData
import com.example.bitclient.data.models.usermodel.UserModel

interface UserManager : UserManagerDelegate {
    fun loginUser(userModel: UserModel)
    fun logout()
}

interface UserManagerDelegate {
    val userModel: LiveData<UserModel>
}