package com.example.bitclient.data.network.requests

import com.example.bitclient.data.storage.Storage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val ACCESS_TOKEN_KEY = "access_token"
private const val REQUEST_TYPE = "Authorization"
private const val TOKEN_TYPE = "Bearer"

class RequestsInterceptor @Inject constructor(storage: Storage) : Interceptor {

    private val accessToken = storage.getString(ACCESS_TOKEN_KEY)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header(REQUEST_TYPE, "$TOKEN_TYPE $accessToken").build()
        return chain.proceed(request)
    }
}