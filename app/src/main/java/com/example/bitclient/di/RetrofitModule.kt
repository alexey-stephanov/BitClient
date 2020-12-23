package com.example.bitclient.di

import com.example.bitclient.data.oauth.AuthorizationInterceptor
import com.example.bitclient.data.oauth.RequestsInterceptor
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.example.bitclient.data.storage.Storage
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Authorization
    @Provides
    fun provideAuthorizationRetrofit(@Authorization okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
            .baseUrl("https://bitbucket.org/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Singleton
    @Requests
    @Provides
    fun provideRequestsRetrofit(@Requests okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
            .baseUrl("https://api.bitbucket.org/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Singleton
    @Authorization
    @Provides
    fun provideAuthorizationClient(
            @Authorization interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Requests
    @Provides
    fun provideRequestsClient(
            @Requests interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Authorization
    @Provides
    fun provideAuthorizationInterceptor(): Interceptor = AuthorizationInterceptor()

    @Singleton
    @Requests
    @Provides
    fun provideRequestsInterceptor(storage: Storage, networkDataRepository: NetworkDataRepository): Interceptor = RequestsInterceptor(storage, networkDataRepository)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}