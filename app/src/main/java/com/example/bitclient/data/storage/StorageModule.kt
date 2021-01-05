package com.example.bitclient.data.storage

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StorageModule {
    @Singleton
    @Binds
    abstract fun bindTokensStorage(storage: SharedPreferencesStorage): Storage
}