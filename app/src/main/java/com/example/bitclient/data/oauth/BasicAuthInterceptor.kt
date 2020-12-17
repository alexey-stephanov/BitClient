package com.example.bitclient.data.oauth

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class BasicAuthInterceptor(clientId: String, clientSecret: String) : Interceptor {
    private var credentials: String = Credentials.basic(clientId, clientSecret)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}