package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.repositories.account.AccountRepository
import com.example.bitclient.data.user.UserAccountLiveDataDelegate
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {

    val accountData = MutableLiveData<AccountDbModel>()

    init {
        viewModelScope.launch {
            val userInfo = accountRepository.retrieveUserInfoFromNetwork()
            accountRepository.saveUserInfoIntoDatabase(userInfo)
            accountData.postValue(userInfo)
        }
    }
}