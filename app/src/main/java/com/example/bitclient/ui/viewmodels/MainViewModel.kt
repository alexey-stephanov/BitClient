package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.example.bitclient.data.repositories.NetworkDataRepositoryImpl
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val networkDataRepository: NetworkDataRepository, private val userManager: UserManager) : ViewModel() {

    fun handleAuthorizationCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            networkDataRepository.retrieveTokens(code)
        }
    }

    fun saveUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = networkDataRepository.retrieveUserInfo()
            userManager.loginUser(result.displayName, "")
        }
    }
}