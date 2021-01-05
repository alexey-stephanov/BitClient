package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel
import com.example.bitclient.data.network.requests.RequestsDataRepository
import com.example.bitclient.data.user.UserLiveDataDelegate
import com.example.bitclient.data.user.UserManager
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val requestsDataRepository: RequestsDataRepository, private val userManager: UserManager) : ViewModel(),
    UserLiveDataDelegate by userManager {

    private suspend fun loadUserData() {
        val userInfo = getUserInfo()
    }

    private suspend fun getUserInfo(): UserModel = requestsDataRepository.retrieveUserInfo()
    }