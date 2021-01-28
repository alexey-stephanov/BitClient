package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.database.UserInfoDao
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.requests.UserDataRepository
import com.example.bitclient.data.user.UserInfoLiveDataDelegate
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userManager: UserManager,
    private val userInfoDao: UserInfoDao
) : ViewModel(),
    UserInfoLiveDataDelegate by userManager {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userInfo = userDataRepository.retrieveUserInfo()
            liveUserModel.postValue(userInfo)
        }
    }

    private fun setUserInfoInDatabase(userInfo: UserModel) {
        userInfoDao.insertUser(userInfo)
    }

    fun getUserInfoFromDatabase(): List<UserModel> = userInfoDao.getAllUsers()
}