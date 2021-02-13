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

    val networkStatus: MutableLiveData<Boolean> = MutableLiveData()

    init {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val userInfo = accountRepository.retrieveUserInfoFromDatabase()
                liveAccountModel.postValue(userInfo)
            }
        } catch (e: NoSuchElementException) {
            loadData()
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (NetworkStatus.isNetworkAvailable()) {
                val userInfo = accountRepository.retrieveUserInfoFromNetwork()
                accountRepository.saveUserInfoInDatabase(userInfo)
            } else {
                networkStatus.postValue(false)
            }
        }
    }
}