package com.example.bitclient.data.network

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(): TokenManager {
    override val tokenStatusLiveData: MutableLiveData<TokenStatus> = MutableLiveData()
}