package com.example.bitclient.di.user.settings

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.repositories.settings.SettingsRepository
import com.example.bitclient.data.repositories.settings.SettingsRepositoryImpl
import com.example.bitclient.di.ViewModelKey
import com.example.bitclient.viewmodels.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SettingsModule {
    @SettingsScope
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @SettingsScope
    @Binds
    fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository
}