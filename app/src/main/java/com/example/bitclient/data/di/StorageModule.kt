package com.example.bitclient.data.di

import com.example.bitclient.data.storage.SharedPreferencesStorage
import com.example.bitclient.data.storage.Storage
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StorageModule {
    @Singleton
    @Binds
    abstract fun bindTokensStorage(storage: SharedPreferencesStorage): Storage
}