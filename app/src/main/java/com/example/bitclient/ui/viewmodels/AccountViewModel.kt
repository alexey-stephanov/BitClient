package com.example.bitclient.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.requests.UserDataRepository
import com.example.bitclient.data.user.UserInfoLiveDataDelegate
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userManager: UserManager
) : ViewModel(),
    UserInfoLiveDataDelegate by userManager {

    init {
        viewModelScope.launch {
            val userInfo = userDataRepository.retrieveUserInfo()
            liveUserModel.postValue(userInfo)
        }
    }
}