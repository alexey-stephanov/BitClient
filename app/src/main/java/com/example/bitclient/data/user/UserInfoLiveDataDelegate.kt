package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.datamodels.usermodel.UserModel

interface UserInfoLiveDataDelegate {
    val liveUserModel: MutableLiveData<UserModel>
}