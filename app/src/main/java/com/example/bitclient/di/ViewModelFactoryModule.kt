package com.example.bitclient.di

import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.viewmodels.factories.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ViewModelFactoryModule {
    @Singleton
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}