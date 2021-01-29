package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.datamodels.TokensModel
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

private const val REQUEST_TYPE = "Authorization"

class AccessTokenAuthenticator @Inject constructor(
    private val networkRepositoryWrapper: Lazy<NetworkRepository>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {

        var result: TokensModel?
        runBlocking {
            result = networkRepositoryWrapper.get().refreshAccessToken()
        }

        return response.request.newBuilder()
            .header(REQUEST_TYPE, "Bearer ${result?.accessToken ?: ""}")
            .build()
    }
}