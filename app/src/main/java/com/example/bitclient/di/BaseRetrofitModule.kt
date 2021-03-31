package com.example.bitclient.di

import com.example.bitclient.data.network.interceptors.AuthorizationInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface BaseRetrofitModule {

    @Singleton
    @AuthorizationQualifier
    @Binds
    fun bindAuthorizationInterceptor(authorizationInterceptor: AuthorizationInterceptor): Interceptor

    companion object {
        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        @Provides
        fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
    }
}