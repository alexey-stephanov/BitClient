package com.example.bitclient.ui.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.data.repositories.NetworkDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val networkDataRepository: NetworkDataRepository): ViewModel() {

    fun handleAuthorizationCode(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = networkDataRepository.retrieveAccessToken(code)
            Log.e("TOKENS", "${result.accessToken} +++ ${result.refreshToken}")
        }
    }
}