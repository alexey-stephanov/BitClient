package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitclient.BuildConfig
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.example.bitclient.data.user.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(private val networkDataRepository: NetworkDataRepository, private val userManager: UserManager) : ViewModel() {

    private val clientId = BuildConfig.CLIENT_ID
    val authorizationUrl = "https://bitbucket.org/site/oauth2/authorize?client_id=$clientId&response_type=code"
}