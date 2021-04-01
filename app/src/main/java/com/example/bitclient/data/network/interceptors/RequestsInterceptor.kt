package com.example.bitclient.data.network.interceptors

import com.example.bitclient.data.storage.Storage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val ACCESS_TOKEN_KEY = "access_token"
private const val REQUEST_TYPE = "Authorization"
private const val BEARER_AUTH = "Bearer"

class RequestsInterceptor @Inject constructor(private val storage: Storage) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = storage.getString(ACCESS_TOKEN_KEY)

        return chain.proceed(
            chain.request().newBuilder().header(REQUEST_TYPE, "$BEARER_AUTH $accessToken").build()
        )
    }
}