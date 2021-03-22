package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.data.repositories.account.AccountRepository
import com.example.bitclient.data.repositories.account.AccountRepositoryImpl
import com.example.bitclient.data.user.UserAccountLiveDataDelegate
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val userManager: UserManager,
) : ViewModel(),
    UserAccountLiveDataDelegate by userManager {

    val networkStatus: MutableLiveData<Boolean> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { context, throwable ->

        }) {
            accountRepository.retrieveAccountInfo().collect { accountInfo ->
                liveAccountModel.postValue(accountInfo)
                Timber.e("Data has loaded.")
            }
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (NetworkStatus.isNetworkAvailable()) {
                val userInfo = accountRepository.retrieveUserInfoFromNetwork()
                accountRepository.saveUserInfoIntoDatabase(userInfo)
            } else {
                networkStatus.postValue(false)
            }
        }
    }
}