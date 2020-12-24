package com.example.bitclient.di

import com.example.bitclient.data.api.AuthApi
import com.example.bitclient.data.api.RequestApi
import com.example.bitclient.data.repositories.NetworkDataRepository
import com.example.bitclient.data.repositories.NetworkDataRepositoryImpl
import com.example.bitclient.data.storage.SharedPreferencesStorage
import com.example.bitclient.data.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class NetworkModule {

//    @Provides
//    @Named("serverUrl")
//    fun provideServerUrl(): String = "https://bitbucket.org/site/oauth2/"

    @Singleton
    @Binds
    abstract fun bindNetworkDataRepository(networkDataRepositoryImpl: NetworkDataRepositoryImpl): NetworkDataRepository

    companion object {
        @Singleton
        @Provides
        fun provideAuthorizationApi(@Authorization retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

        @Singleton
        @Provides
        fun provideApi(@Requests retrofit: Retrofit): RequestApi = retrofit.create(RequestApi::class.java)
    }
}