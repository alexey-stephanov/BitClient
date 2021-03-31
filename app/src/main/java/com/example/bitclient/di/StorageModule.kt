package com.example.bitclient.di

import com.example.bitclient.data.storage.SharedPreferencesStorage
import com.example.bitclient.data.storage.Storage
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface StorageModule {

    @Singleton
    @Binds
    fun bindSharedPreferencesStorage(sharedPreferencesStorage: SharedPreferencesStorage): Storage
}