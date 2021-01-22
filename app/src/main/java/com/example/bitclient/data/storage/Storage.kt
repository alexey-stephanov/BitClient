package com.example.bitclient.data.storage

import com.example.bitclient.data.network.datamodels.TokensModel

interface Storage {
    fun getString(key: String): String
    fun setString(key: String, value: String)
    fun saveTokens(tokensModel: TokensModel)
    fun clearStorage()
}