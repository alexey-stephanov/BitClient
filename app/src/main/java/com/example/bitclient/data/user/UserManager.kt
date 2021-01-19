package com.example.bitclient.data.user

import com.example.bitclient.data.network.networkmodels.usermodel.UserModel

interface UserManager : UserInfoLiveDataDelegate {
    fun loginUser(userModel: UserModel)
    fun logout()
}