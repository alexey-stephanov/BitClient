package com.example.bitclient.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.repositories.account.AccountRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val accountData: MutableLiveData<AccountDbModel> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Timber.e("Exception handled $throwable")
        }) {
            accountRepository.retrieveAccountInfo().collect { accountInfo ->
                accountData.postValue(accountInfo)
            }
        }
    }
}