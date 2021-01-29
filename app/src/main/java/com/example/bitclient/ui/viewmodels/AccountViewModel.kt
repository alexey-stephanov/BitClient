package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.repositories.account.AccountRepository
import com.example.bitclient.data.user.UserInfoLiveDataDelegate
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userManager: UserManager,
) : ViewModel(),
    UserInfoLiveDataDelegate by userManager {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userInfo = accountRepository.retrieveUserInfo()
            liveUserModel.postValue(userInfo)
        }
    }

//    private fun setUserInfoInDatabase(userInfo: UserModel) {
//        userInfoDao.insertUser(userInfo)
//    }
//
//    fun getUserInfoFromDatabase(): List<UserModel> = userInfoDao.getAllUsers()
}