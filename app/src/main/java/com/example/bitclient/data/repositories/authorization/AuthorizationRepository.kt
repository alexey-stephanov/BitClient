package com.example.bitclient.data.repositories.authorization

import com.example.bitclient.data.network.datamodels.tokensmodel.TokensModel

interface AuthorizationRepository {
    suspend fun retrieveTokens(code: String): TokensModel
}