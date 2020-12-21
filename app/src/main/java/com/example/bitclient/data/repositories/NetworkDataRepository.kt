package com.example.bitclient.data.repositories

import com.example.bitclient.data.api.AuthApi
import com.example.bitclient.data.api.RequestApi
import com.example.bitclient.data.storage.Storage
import com.example.bitclient.di.BasicAuth
import com.example.bitclient.di.BearerAuth
import javax.inject.Inject
import javax.inject.Singleton

private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"

@Singleton
class NetworkDataRepository @Inject constructor(
        private val storage: Storage,
        @BasicAuth private val basicService: AuthApi,
        @BearerAuth private val bearerService: RequestApi,
) {
    suspend fun retrieveAccessToken(code: String) {
        val result = basicService.getAccessToken("authorization_code", code)
        putTokensIntoStorage(result.accessToken, result.refreshToken)
    }

    suspend fun getUserInfo() {
        val result = bearerService.getUserInfo()
    }

    private fun putTokensIntoStorage(accessToken: String, refreshToken: String) {
        storage.setString(ACCESS_TOKEN_KEY, accessToken)
        storage.setString(REFRESH_TOKEN_KEY, refreshToken)
    }
}