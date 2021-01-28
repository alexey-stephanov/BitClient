package com.example.bitclient.data.di.authorization

import com.example.bitclient.BuildConfig
import com.example.bitclient.data.di.AuthorizationQualifier
import com.example.bitclient.data.network.authorization.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class AuthorizationRequestsModule {

    @AuthorizationScope
    @Binds
    abstract fun bindAuthorizationDataRepository(authorizationDataRepositoryImpl: AuthorizationDataRepositoryImpl): AuthorizationDataRepository

    companion object {
        @AuthorizationScope
        @Provides
        fun provideAuthorizationApi(retrofit: Retrofit): AuthorizationApi =
            retrofit.create(AuthorizationApi::class.java)

        @AuthorizationScope
        @Provides
        fun provideAuthorizationRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
        ): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

        @AuthorizationScope
        @Provides
        fun provideAuthorizationClient(
            @AuthorizationQualifier interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}