package com.example.bitclient.di

import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}