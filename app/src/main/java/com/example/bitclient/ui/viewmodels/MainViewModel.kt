package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.authorization.AuthorizationDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authorizationDataRepository: AuthorizationDataRepository
) : ViewModel() {

    fun handleAuthorizationCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authorizationDataRepository.retrieveTokens(code)
        }
    }
}