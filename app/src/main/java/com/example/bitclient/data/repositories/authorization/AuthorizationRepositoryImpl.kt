package com.example.bitclient.data.repositories.authorization

import com.example.bitclient.data.network.api.AuthorizationApi
import com.example.bitclient.data.network.datamodels.tokensmodel.TokensModel
import javax.inject.Inject

private const val GRANT_TYPE = "authorization_code"

class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationService: AuthorizationApi
) : AuthorizationRepository {

    override suspend fun retrieveTokens(code: String): TokensModel {
        return authorizationService.getAccessToken(GRANT_TYPE, code)
    }
}