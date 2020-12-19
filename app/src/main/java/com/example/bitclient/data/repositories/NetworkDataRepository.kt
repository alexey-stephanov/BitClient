package com.example.bitclient.data.repositories

import android.util.Log
import com.example.bitclient.data.api.NetworkApi
import com.example.bitclient.data.models.TokenModel
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://bitbucket.org/site/oauth2/"
private const val clientId = "cDXfEkaZFs8aSvnVgT"
private const val client_secret = "zz2CnTxvkSheRZkB83km3qrrXj4ju56B"
private const val response_type = "code"
private const val grant_type = "authorization_code"

@Singleton
class NetworkDataRepository @Inject constructor(private val service: NetworkApi) {

    val authorizationUrl = "${BASE_URL}authorize?client_id=$clientId&response_type=$response_type"

    suspend fun retrieveAccessToken(code: String) {
        val result =  service.getAccessToken(grant_type, code)
        Log.d("TOKENS", "access_token: ${result.accessToken}, refresh_token: ${result.refreshToken}")
    }
}