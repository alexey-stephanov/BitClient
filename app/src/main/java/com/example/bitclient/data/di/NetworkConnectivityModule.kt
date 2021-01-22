package com.example.bitclient.data.di

import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NetworkConnectivityModule {

    @Singleton
    @Binds
    abstract fun bindNetworkConnectivityManager(networkConnectivityManagerImpl: NetworkConnectivityManagerImpl): NetworkConnectivityManager
}