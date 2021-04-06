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

class AccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val accountData: MutableLiveData<AccountDbModel> = MutableLiveData()

    val exceptionMessage: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Timber.e("Exception handled $throwable")
        }) {
            accountRepository.retrieveAccountInfo().collect { accountInfo ->
                accountData.postValue(accountInfo)
            }
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            exceptionMessage.postValue(throwable.localizedMessage)
        }) {
            accountData.postValue(accountRepository.updateAccountInfo())
        }
    }
}