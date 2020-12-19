package com.example.bitclient.di

import com.example.bitclient.data.api.NetworkApi
import com.example.bitclient.data.oauth.BasicAuthInterceptor
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)

    @Singleton
    @Provides
    fun provideBasicAuthInterceptor(): BasicAuthInterceptor = BasicAuthInterceptor("cDXfEkaZFs8aSvnVgT", "zz2CnTxvkSheRZkB83km3qrrXj4ju56B")

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(basicAuthInterceptor: BasicAuthInterceptor,
                        httpLoggingInterceptor: HttpLoggingInterceptor,
                        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://bitbucket.org/site/oauth2/")
        .client(OkHttpClient.Builder()
            .addInterceptor(basicAuthInterceptor)
            .addInterceptor(httpLoggingInterceptor).build())
        .addConverterFactory(gsonConverterFactory)
        .build()
}