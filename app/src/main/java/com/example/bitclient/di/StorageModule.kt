package com.example.bitclient.di

import android.content.Context
import com.example.bitclient.data.storage.SharedPreferencesStorage
import com.example.bitclient.data.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class StorageModule {
    @Singleton
    @Binds
    abstract fun bindTokensStorage(storage: SharedPreferencesStorage): Storage
}