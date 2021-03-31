package com.example.bitclient.di

import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManager
import com.example.bitclient.data.network.networkavailability.NetworkConnectivityManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NetworkConnectivityModule {

    @Singleton
    @Binds
    fun bindNetworkConnectivityManager(networkConnectivityManagerImpl: NetworkConnectivityManagerImpl): NetworkConnectivityManager
}