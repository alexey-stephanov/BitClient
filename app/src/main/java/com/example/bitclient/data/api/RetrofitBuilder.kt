package com.example.bitclient.data.api

import retrofit2.Retrofit

object RetrofitBuilder {
    private const val BASE_URL = "https://bitbucket.org/site/oauth2/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()

    fun getService(): NetworkApi = retrofit.create(NetworkApi::class.java)
}