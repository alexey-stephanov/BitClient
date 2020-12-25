package com.example.bitclient.data.oauth

import android.util.Log
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.example.bitclient.data.repositories.NetworkDataRepositoryImpl
import com.example.bitclient.data.storage.Storage
import com.example.bitclient.di.Requests
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject

@Requests
class RequestsInterceptor @Inject constructor(storage: Storage) : Interceptor {

    private val accessToken = storage.getString("access_token")

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e("QWE", accessToken)
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Bearer $accessToken").build()
        return chain.proceed(request)
    }
}