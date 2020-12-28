package com.example.bitclient.data.oauth

import com.example.bitclient.data.api.AuthApi
import com.example.bitclient.data.models.TokenModel
import com.example.bitclient.data.storage.Storage
import com.example.bitclient.di.Requests
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

@Requests
class AccessTokenAuthenticator @Inject constructor(
    private val storage: Storage,
    private val authServiceWrapper: Lazy<AuthApi>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        val authService = authServiceWrapper.get()
        val refreshToken = storage.getString("refresh_token")
        var result: TokenModel?

        runBlocking {
            result = authService.refreshAccessToken("refresh_token", refreshToken)
        }

        storage.setString("access_token", result!!.accessToken)

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${result!!.accessToken}")
            .build()
    }

}