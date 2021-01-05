package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.user.UserScope
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserViewModelsModule {

    @UserScope
    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel

    @UserScope
    @Binds
    @IntoMap
    @ViewModelKey(BranchesViewModel::class)
    abstract fun bindBranchesViewModel(viewModel: BranchesViewModel): ViewModel

    @UserScope
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @UserScope
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel
}