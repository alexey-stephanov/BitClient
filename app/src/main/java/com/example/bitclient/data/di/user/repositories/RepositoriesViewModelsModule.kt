package com.example.bitclient.data.di.user.repositories

import androidx.lifecycle.ViewModel
import com.example.bitclient.ui.viewmodels.BranchesViewModel
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import com.example.bitclient.data.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RepositoriesViewModelsModule {
    @RepositoriesScope
    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel

    @RepositoriesScope
    @Binds
    @IntoMap
    @ViewModelKey(BranchesViewModel::class)
    abstract fun bindBranchesViewModel(viewModel: BranchesViewModel): ViewModel
}