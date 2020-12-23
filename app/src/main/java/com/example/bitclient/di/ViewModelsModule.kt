package com.example.bitclient.di

import androidx.lifecycle.ViewModel
import com.example.bitclient.ui.viewmodels.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    abstract fun bindAuthorizationViewModel(viewModel: AuthorizationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BranchesViewModel::class)
    abstract fun bindBranchesViewModel(viewModel: BranchesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}