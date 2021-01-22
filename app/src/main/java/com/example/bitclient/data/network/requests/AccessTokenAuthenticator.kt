package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.datamodels.TokensModel
import com.example.bitclient.data.storage.Storage
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

private const val GRANT_TYPE = "refresh_token"
private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"
private const val REQUEST_TYPE = "Authorization"
private const val TOKEN_TYPE = "Bearer"

class AccessTokenAuthenticator @Inject constructor(
    private val storage: Storage,
    private val requestsServiceWrapper: Lazy<RequestsApi>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        val service = requestsServiceWrapper.get()
        val refreshToken = storage.getString(REFRESH_TOKEN_KEY)
        var result: TokensModel?

        runBlocking {
            result = service.refreshAccessToken(GRANT_TYPE, refreshToken)
        }

        storage.setString(ACCESS_TOKEN_KEY, result!!.accessToken)

        return response.request.newBuilder()
            .header(REQUEST_TYPE, "$TOKEN_TYPE ${result!!.accessToken}")
            .build()
    }

}