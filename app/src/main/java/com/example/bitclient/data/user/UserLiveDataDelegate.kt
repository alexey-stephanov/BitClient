package com.example.bitclient.data.user

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel

interface UserLiveDataDelegate {
    val liveUserModel: MutableLiveData<UserModel>
    val liveUserRepositories: MutableLiveData<List<RepositoryModel>>
}