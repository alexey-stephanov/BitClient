package com.example.bitclient.di.user

import com.example.bitclient.BuildConfig
import com.example.bitclient.data.network.api.RequestsApi
import com.example.bitclient.data.network.authenticators.AccessTokenAuthenticator
import com.example.bitclient.data.network.interceptors.RequestsInterceptor
import com.example.bitclient.di.RequestQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface RequestsModule {

    @UserScope
    @RequestQualifier
    @Binds
    fun bindRequestsInterceptor(requestsInterceptor: RequestsInterceptor): Interceptor

    @UserScope
    @Binds
    fun bindAccessTokenAuthenticator(accessTokenAuthenticator: AccessTokenAuthenticator): Authenticator

    companion object {
        @UserScope
        @RequestQualifier
        @Provides
        fun provideRequestsApi(@RequestQualifier retrofit: Retrofit): RequestsApi =
            retrofit.create(RequestsApi::class.java)

        @UserScope
        @RequestQualifier
        @Provides
        fun provideRequestsRetrofit(
            @RequestQualifier okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
        ): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.REQUESTS_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

        @UserScope
        @RequestQualifier
        @Provides
        fun provideRequestsClient(
            @RequestQualifier interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
            authenticator: Authenticator
        ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .authenticator(authenticator)
            .build()
    }
}