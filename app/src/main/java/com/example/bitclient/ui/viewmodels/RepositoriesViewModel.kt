package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitclient.data.models.usermodel.UserModel
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.example.bitclient.data.user.UserManager
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val networkDataRepository: NetworkDataRepository, private val userManager: UserManager) : ViewModel() {

    val userModel: MutableLiveData<UserModel> by lazy {
        MutableLiveData<UserModel>()
    }

    //fun getUserInfo(): String = userManager.getUserInfo()
}