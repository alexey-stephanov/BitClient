package com.example.bitclient.data.network.authorization

import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.networkmodels.TokensModel

interface TokensLiveDataDelegate {
    val liveUserTokens: MutableLiveData<TokensModel>
}