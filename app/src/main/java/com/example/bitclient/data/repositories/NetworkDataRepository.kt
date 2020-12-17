package com.example.bitclient.data.repositories

import com.example.bitclient.BitClientApp
import com.example.bitclient.data.api.NetworkApi
import com.example.bitclient.data.model.TokenModel
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://bitbucket.org/site/oauth2/"
private const val clientId = "cDXfEkaZFs8aSvnVgT"
private const val client_secret = "zz2CnTxvkSheRZkB83km3qrrXj4ju56B"
private const val response_type = "code"

@Singleton
class NetworkDataRepository @Inject constructor(private val retrofit: Retrofit) {

    val authorizationUrl = "${BASE_URL}authorize?client_id=$clientId&response_type=$response_type"

    private val service = retrofit.create(NetworkApi::class.java)

    suspend fun retrieveAccessToken(code: String): TokenModel {
        return service.getAccessToken("authorization_code", code)
    }
}