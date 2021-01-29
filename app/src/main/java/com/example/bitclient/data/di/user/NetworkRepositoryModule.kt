package com.example.bitclient.data.di.user

import com.example.bitclient.data.network.requests.NetworkRepository
import com.example.bitclient.data.network.requests.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkRepositoryModule {

    @UserScope
    @Binds
    abstract fun bindNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository
}