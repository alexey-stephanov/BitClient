package com.example.bitclient.data.network.requests

import com.example.bitclient.data.di.AuthorizationQualifier
import com.example.bitclient.data.network.datamodels.TokensModel
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

private const val GRANT_TYPE = "refresh_token"
private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"

class NetworkRepositoryImpl @Inject constructor(
    @AuthorizationQualifier private val refreshService: RefreshApi,
    private val storage: Storage
) : NetworkRepository {

    override suspend fun refreshAccessToken(): TokensModel {
        val refreshToken = storage.getString(REFRESH_TOKEN_KEY)
        val result = refreshService.refreshAccessToken(GRANT_TYPE, refreshToken)
        storage.setString(ACCESS_TOKEN_KEY, result.accessToken)
        return result
    }
}