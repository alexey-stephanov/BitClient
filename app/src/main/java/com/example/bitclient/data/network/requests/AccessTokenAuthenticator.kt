package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.networkmodels.TokensModel
import com.example.bitclient.data.storage.Storage
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AccessTokenAuthenticator @Inject constructor(
    private val storage: Storage,
    private val requestsServiceWrapper: Lazy<RequestsApi>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        val service = requestsServiceWrapper.get()
        val refreshToken = storage.getString("refresh_token")
        var result: TokensModel?

        runBlocking {
            result = service.refreshAccessToken("refresh_token", refreshToken)
        }

        storage.setString("access_token", result!!.accessToken)

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${result!!.accessToken}")
            .build()
    }

}