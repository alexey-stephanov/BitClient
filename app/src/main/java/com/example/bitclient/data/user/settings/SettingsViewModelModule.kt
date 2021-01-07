package com.example.bitclient.data.user.settings

import androidx.lifecycle.ViewModel
import com.example.bitclient.ui.viewmodels.SettingsViewModel
import com.example.bitclient.ui.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SettingsViewModelModule {
    @SettingsScope
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}