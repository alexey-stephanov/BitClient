package com.example.bitclient.di.user.settings

import androidx.lifecycle.ViewModel
import com.example.bitclient.di.ViewModelKey
import com.example.bitclient.viewmodels.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SettingsModule {
    @SettingsScope
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}