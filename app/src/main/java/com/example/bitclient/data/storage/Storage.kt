package com.example.bitclient.data.storage

import com.example.bitclient.data.network.networkmodels.TokenModel

interface Storage {
    fun getString(key: String): String
    fun setString(key: String, value: String)
    fun saveTokens(tokenModel: TokenModel)
}