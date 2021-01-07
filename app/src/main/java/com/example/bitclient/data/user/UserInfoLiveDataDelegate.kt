package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel

interface UserInfoLiveDataDelegate {
    val liveUserModel: MutableLiveData<UserModel>
}