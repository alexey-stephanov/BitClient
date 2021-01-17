package com.example.bitclient.data.user

import com.example.bitclient.data.network.networkmodels.usermodel.UserModel

interface UserManager : UserInfoLiveDataDelegate, UserWorkspacesLiveDataDelegate {
    fun loginUser(userModel: UserModel)
    fun logout()
    fun pushId(id: String)
}