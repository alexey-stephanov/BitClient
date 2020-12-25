package com.example.bitclient.di

import com.example.bitclient.BuildConfig
import com.example.bitclient.data.oauth.AccessTokenAuthenticator
import com.example.bitclient.data.oauth.AuthorizationInterceptor
import com.example.bitclient.data.oauth.RequestsInterceptor
import com.example.bitclient.data.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class RetrofitModule {

    @Authorization
    @Binds
    abstract fun bindAuthorizationInterceptor(authorizationInterceptor: AuthorizationInterceptor): Interceptor

    @Requests
    @Binds
    abstract fun bindRequestsInterceptor(requestsInterceptor: RequestsInterceptor): Interceptor

    @Requests
    @Binds
    abstract fun bindAccessTokenAuthenticator(accessTokenAuthenticator: AccessTokenAuthenticator): Authenticator

    companion object {
        @Singleton
        @Authorization
        @Provides
        fun provideAuthorizationRetrofit(@Authorization okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
        ): Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.AUTH_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()

        @Singleton
        @Requests
        @Provides
        fun provideRequestsRetrofit(@Requests okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
        ): Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.REQUESTS_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()

        @Authorization
        @Provides
        fun provideAuthorizationClient(
                @Authorization interceptor: Interceptor,
                httpLoggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

        @Requests
        @Provides
        fun provideRequestsClient(
                @Requests interceptor: Interceptor,
                httpLoggingInterceptor: HttpLoggingInterceptor,
                storage: Storage
        ): OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .authenticator(AccessTokenAuthenticator(storage))
                .build()

        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        @Provides
        fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
    }
}