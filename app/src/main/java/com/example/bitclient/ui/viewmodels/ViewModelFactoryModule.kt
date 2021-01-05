package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModelProvider
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {
    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}