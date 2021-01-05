package com.example.bitclient.data.network.authorization

import com.example.bitclient.BuildConfig
import com.example.bitclient.data.network.authorization.AuthorizationQualifier
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@AuthorizationQualifier
class AuthorizationInterceptor @Inject constructor(): Interceptor {
    private var credentials: String = Credentials.basic(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}