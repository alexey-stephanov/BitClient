package com.example.bitclient.data.network.requests

import com.example.bitclient.data.storage.Storage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestsInterceptor @Inject constructor(storage: Storage) : Interceptor {

    private val accessToken = storage.getString("access_token")

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Bearer $accessToken").build()
        return chain.proceed(request)
    }
}