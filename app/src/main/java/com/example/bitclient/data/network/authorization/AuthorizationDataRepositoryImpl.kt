package com.example.bitclient.data.network.authorization

import com.example.bitclient.data.network.networkmodels.TokensModel
import javax.inject.Inject

private const val GRANT_TYPE = "authorization_code"

class AuthorizationDataRepositoryImpl @Inject constructor(
    private val authorizationService: AuthorizationApi
) : AuthorizationDataRepository {

    override suspend fun retrieveTokens(code: String): TokensModel {
        return authorizationService.getAccessToken(GRANT_TYPE, code)
    }
}