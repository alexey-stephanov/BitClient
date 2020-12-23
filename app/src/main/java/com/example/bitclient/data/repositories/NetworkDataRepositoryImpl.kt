package com.example.bitclient.data.repositories

import com.example.bitclient.data.api.AuthApi
import com.example.bitclient.data.api.RequestApi
import com.example.bitclient.data.models.UserModel
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject
import javax.inject.Singleton

private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"

@Singleton
class NetworkDataRepositoryImpl @Inject constructor(
        private val storage: Storage,
        private val authService: AuthApi,
        private val service: RequestApi,
) : NetworkDataRepository {
    override suspend fun retrieveTokens(code: String) {
        val result = authService.getAccessToken("authorization_code", code)
        putTokensIntoStorage(result.accessToken, result.refreshToken)
    }

    override suspend fun refreshAccessToken() {
        val refreshToken = storage.getString("refresh_token")
        val result = authService.refreshAccessToken("refresh_token", refreshToken)
        putTokensIntoStorage(result.accessToken, result.refreshToken)
    }

    override suspend fun retrieveUserInfo() : UserModel = service.getUserInfo()

    private fun putTokensIntoStorage(accessToken: String, refreshToken: String) {
        storage.setString(ACCESS_TOKEN_KEY, accessToken)
        storage.setString(REFRESH_TOKEN_KEY, refreshToken)
    }
}