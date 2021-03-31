package com.example.bitclient.data.network.authenticators

import com.example.bitclient.data.network.api.RefreshApi
import com.example.bitclient.data.network.datamodels.tokensmodel.TokensModel
import com.example.bitclient.data.storage.Storage
import com.example.bitclient.di.AuthorizationQualifier
import dagger.Lazy
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

private const val REQUEST_TYPE = "Authorization"
private const val GRANT_TYPE = "refresh_token"
private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"

class AccessTokenAuthenticator @Inject constructor(
    private val storage: Storage,
    @AuthorizationQualifier private val refreshServiceWrapper: Lazy<RefreshApi>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {

        val result: TokensModel
        val refreshToken = storage.getString(REFRESH_TOKEN_KEY)

        runBlocking {
            result = refreshTokenAsync(refreshServiceWrapper.get(), refreshToken).await()
            storage.setString(ACCESS_TOKEN_KEY, result.accessToken)
        }

        return response.request.newBuilder()
            .header(REQUEST_TYPE, "Bearer ${result.accessToken}")
            .build()
    }

    private fun refreshTokenAsync(refreshService: RefreshApi, refreshToken: String) =
        GlobalScope.async {
            refreshService.refreshAccessToken(GRANT_TYPE, refreshToken)
        }
}