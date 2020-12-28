package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserManagerDelegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private val networkDataRepository: NetworkDataRepository,
                                        private val userManager: UserManager) : ViewModel(), UserManagerDelegate by userManager {

    fun handleAuthorizationCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            networkDataRepository.retrieveTokens(code)
            saveUserInfo()
        }
    }

    private fun saveUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = networkDataRepository.retrieveUserInfo()
            userManager.loginUser(result)
        }
    }
}