package com.example.bitclient.di

import com.example.bitclient.data.api.NetworkApi
import com.example.bitclient.data.oauth.BasicAuthInterceptor
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level
import javax.inject.Singleton

@Module
class NetworkModule {

//    @Singleton
//    @Provides
//    fun provideApi(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://bitbucket.org/site/oauth2/")
        .client(OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor("cDXfEkaZFs8aSvnVgT", "zz2CnTxvkSheRZkB83km3qrrXj4ju56B"))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
        //.client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}