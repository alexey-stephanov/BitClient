package com.example.bitclient.data.network

import androidx.lifecycle.MutableLiveData

interface TokenManager {
    val tokenStatusLiveData: MutableLiveData<TokenStatus>
}