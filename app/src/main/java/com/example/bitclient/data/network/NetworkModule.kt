package com.example.bitclient.data.network

import com.example.bitclient.data.network.authorization.AuthorizationApi
import com.example.bitclient.data.network.authorization.AuthorizationQualifier
import com.example.bitclient.data.network.authorization.AuthorizationDataRepository
import com.example.bitclient.data.network.authorization.AuthorizationDataRepositoryImpl
import com.example.bitclient.data.network.requests.RequestsApi
import com.example.bitclient.data.network.requests.RequestsDataRepository
import com.example.bitclient.data.network.requests.RequestsDataRepositoryImpl
import com.example.bitclient.data.network.requests.RequestsQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun bindAuthorizationDataRepository(authorizationDataRepositoryImpl: AuthorizationDataRepositoryImpl): AuthorizationDataRepository

    @Singleton
    @Binds
    abstract fun bindRequestsDataRepository(requestsDataRepositoryImpl: RequestsDataRepositoryImpl): RequestsDataRepository

    companion object {
        @Singleton
        @Provides
        fun provideAuthorizationApi(@AuthorizationQualifier retrofit: Retrofit): AuthorizationApi =
            retrofit.create(AuthorizationApi::class.java)

        @Singleton
        @Provides
        fun provideApi(@RequestsQualifier retrofit: Retrofit): RequestsApi =
            retrofit.create(RequestsApi::class.java)
    }
}