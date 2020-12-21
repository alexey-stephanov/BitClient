package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bitclient.BuildConfig
import com.example.bitclient.data.repositories.NetworkDataRepository
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(networkDataRepository: NetworkDataRepository) : ViewModel() {

    private val clientId = BuildConfig.CLIENT_ID
    val authorizationUrl = "https://bitbucket.org/site/oauth2/authorize?client_id=$clientId&response_type=code"

    fun startRequest() {

    }

}