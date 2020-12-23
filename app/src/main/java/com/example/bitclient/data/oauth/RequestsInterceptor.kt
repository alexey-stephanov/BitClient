package com.example.bitclient.data.oauth

import com.example.bitclient.data.repositories.NetworkDataRepository
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
class RequestsInterceptor @Inject constructor(storage: Storage, private val networkDataRepository: NetworkDataRepository) : Interceptor {

    private val accessToken = storage.getString("access_token")

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Bearer $accessToken").build()
        var response = chain.proceed(request)
        if(response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            runBlocking {
                networkDataRepository.refreshAccessToken()
                response = intercept(chain)
            }
        }
        return response
    }
}