package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.network.TokenManager
import com.example.bitclient.data.network.TokenStatus
import com.example.bitclient.data.network.authorization.AuthorizationDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authorizationDataRepository: AuthorizationDataRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    fun handleAuthorizationCode(code: String) {
        tokenManager.tokenStatusLiveData.value = TokenStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            authorizationDataRepository.retrieveTokens(code)
            tokenManager.tokenStatusLiveData.postValue(TokenStatus.Ready)
        }
    }
}