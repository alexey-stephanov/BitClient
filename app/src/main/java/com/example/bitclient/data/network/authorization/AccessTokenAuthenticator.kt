package com.example.bitclient.data.network.authorization

import com.example.bitclient.data.network.networkmodels.TokensModel
import com.example.bitclient.data.storage.Storage
import com.example.bitclient.data.network.requests.RequestsQualifier
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

@RequestsQualifier
class AccessTokenAuthenticator @Inject constructor(
    private val storage: Storage,
    private val authorizationServiceWrapper: Lazy<AuthorizationApi>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        val authService = authorizationServiceWrapper.get()
        val refreshToken = storage.getString("refresh_token")
        var result: TokensModel?

        runBlocking {
            result = authService.refreshAccessToken("refresh_token", refreshToken)
        }

        storage.setString("access_token", result!!.accessToken)

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${result!!.accessToken}")
            .build()
    }

}