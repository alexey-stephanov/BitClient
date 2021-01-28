package com.example.bitclient.data.di.user

import com.example.bitclient.BuildConfig
import com.example.bitclient.data.di.AuthorizationQualifier
import com.example.bitclient.data.network.requests.RefreshApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RefreshTokenModule {

    @UserScope
    @AuthorizationQualifier
    @Provides
    fun provideRefreshApi(@AuthorizationQualifier retrofit: Retrofit): RefreshApi =
        retrofit.create(RefreshApi::class.java)

    @UserScope
    @AuthorizationQualifier
    @Provides
    fun provideRefreshRetrofit(
        @AuthorizationQualifier okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @UserScope
    @AuthorizationQualifier
    @Provides
    fun provideRefreshClient(
        @AuthorizationQualifier interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}