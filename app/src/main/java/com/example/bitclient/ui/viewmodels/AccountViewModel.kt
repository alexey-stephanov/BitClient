package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.networkavailability.NetworkStatus
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
        getUserInfo()
    }

    val refreshingStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (NetworkStatus.isNetworkAvailable()) {
                accountRepository.retrieveUserInfoFromNetwork()
            } else {
                refreshingStatus.postValue(false)
            }
        }
    }

    private fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            var userInfo = accountRepository.retrieveUserInfoFromDatabase()
            liveAccountModel.postValue(userInfo)
            if(NetworkStatus.isNetworkAvailable()) {
                userInfo = accountRepository.retrieveUserInfoFromNetwork()
                liveAccountModel.postValue(userInfo)
            }
        }
    }
}