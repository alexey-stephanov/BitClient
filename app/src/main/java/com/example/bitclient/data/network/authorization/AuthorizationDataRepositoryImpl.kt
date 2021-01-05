package com.example.bitclient.data.network.authorization

import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

class AuthorizationDataRepositoryImpl @Inject constructor(
    private val storage: Storage,
    private val authorizationService: AuthorizationApi
) :
    AuthorizationDataRepository {
    override suspend fun retrieveTokens(code: String) {
        val result = authorizationService.getAccessToken("authorization_code", code)
        storage.saveTokens(result)
    }
}