package com.example.bitclient.data.network.authorization

import com.example.bitclient.data.network.networkmodels.TokensModel

interface AuthorizationDataRepository {
    suspend fun retrieveTokens(code: String): TokensModel
}