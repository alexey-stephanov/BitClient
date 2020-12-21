package com.example.bitclient.di

import com.example.bitclient.data.api.AuthApi
import com.example.bitclient.data.api.RequestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

//    @Provides
//    @Named("serverUrl")
//    fun provideServerUrl(): String = "https://bitbucket.org/site/oauth2/"

    @Singleton
    @BasicAuth
    @Provides
    fun provideBasicApi(@BasicAuth retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Singleton
    @BearerAuth
    @Provides
    fun provideBearerApi(@BearerAuth retrofit: Retrofit): RequestApi = retrofit.create(RequestApi::class.java)
}