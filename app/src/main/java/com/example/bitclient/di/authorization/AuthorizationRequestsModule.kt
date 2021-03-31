package com.example.bitclient.di.authorization

import com.example.bitclient.BuildConfig
import com.example.bitclient.di.AuthorizationQualifier
import com.example.bitclient.data.network.api.AuthorizationApi
import com.example.bitclient.data.repositories.authorization.AuthorizationRepository
import com.example.bitclient.data.repositories.authorization.AuthorizationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface AuthorizationRequestsModule {

    @AuthorizationScope
    @Binds
    fun bindAuthorizationDataRepository(authorizationDataRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository

    companion object {
        @AuthorizationScope
        @Provides
        fun provideAuthorizationApi(retrofit: Retrofit): AuthorizationApi =
            retrofit.create(AuthorizationApi::class.java)

        @AuthorizationScope
        @Provides
        fun provideAuthorizationRetrofit(
            okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
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