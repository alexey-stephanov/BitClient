package com.example.bitclient.data.network

import com.example.bitclient.BuildConfig
import com.example.bitclient.data.network.authorization.AuthorizationApi
import com.example.bitclient.data.network.authorization.AuthorizationQualifier
import com.example.bitclient.data.network.authorization.AccessTokenAuthenticator
import com.example.bitclient.data.network.authorization.AuthorizationInterceptor
import com.example.bitclient.data.network.requests.RequestsInterceptor
import com.example.bitclient.data.network.requests.RequestsQualifier
import com.example.bitclient.data.storage.Storage
import dagger.Binds
import dagger.Lazy
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

    @AuthorizationQualifier
    @Binds
    abstract fun bindAuthorizationInterceptor(authorizationInterceptor: AuthorizationInterceptor): Interceptor

    @RequestsQualifier
    @Binds
    abstract fun bindRequestsInterceptor(requestsInterceptor: RequestsInterceptor): Interceptor

    @RequestsQualifier
    @Binds
    abstract fun bindAccessTokenAuthenticator(accessTokenAuthenticator: AccessTokenAuthenticator): Authenticator

    companion object {
        @Singleton
        @AuthorizationQualifier
        @Provides
        fun provideAuthorizationRetrofit(@AuthorizationQualifier okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
        ): Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.AUTH_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()

        @Singleton
        @RequestsQualifier
        @Provides
        fun provideRequestsRetrofit(@RequestsQualifier okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
        ): Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.REQUESTS_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()

        @AuthorizationQualifier
        @Provides
        fun provideAuthorizationClient(
                @AuthorizationQualifier interceptor: Interceptor,
                httpLoggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

        @RequestsQualifier
        @Provides
        fun provideRequestsClient(
            @RequestsQualifier interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor,
            storage: Storage,
            authorizationApi: Lazy<AuthorizationApi>
        ): OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .authenticator(AccessTokenAuthenticator(storage, authorizationApi))
                .build()

        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        @Provides
        fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
    }
}