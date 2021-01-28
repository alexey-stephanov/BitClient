package com.example.bitclient.data.di

import com.example.bitclient.data.network.authorization.AuthorizationInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class BaseRetrofitModule {

    @Singleton
    @AuthorizationQualifier
    @Binds
    abstract fun bindAuthorizationInterceptor(authorizationInterceptor: AuthorizationInterceptor): Interceptor

    companion object {
        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        @Provides
        fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
    }
}