package com.example.bitclient.di

import android.content.Context
import com.example.bitclient.data.storage.SharedPreferencesStorage
import com.example.bitclient.data.storage.Storage
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideTokensStorage(context: Context): Storage =
            SharedPreferencesStorage("tokens", context)
}