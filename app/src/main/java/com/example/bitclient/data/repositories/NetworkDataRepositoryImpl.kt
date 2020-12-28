package com.example.bitclient.data.repositories

import com.example.bitclient.data.api.AuthApi
import com.example.bitclient.data.api.RequestsApi
import com.example.bitclient.data.models.usermodel.UserModel
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"

class NetworkDataRepositoryImpl @Inject constructor(
    private val storage: Storage,
    private val authService: AuthApi,
    private val service: RequestsApi,
) : NetworkDataRepository {
    override suspend fun retrieveTokens(code: String) {
        val result = authService.getAccessToken("authorization_code", code)
        putTokensIntoStorage(result.accessToken, result.refreshToken)
    }

    override suspend fun retrieveUserInfo() : UserModel = service.getUserInfo()

    private fun putTokensIntoStorage(accessToken: String, refreshToken: String) {
        storage.setString(ACCESS_TOKEN_KEY, accessToken)
        storage.setString(REFRESH_TOKEN_KEY, refreshToken)
    }
}