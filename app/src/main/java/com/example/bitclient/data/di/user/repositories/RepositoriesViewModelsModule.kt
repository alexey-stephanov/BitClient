package com.example.bitclient.data.di.user.repositories

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.di.ViewModelKey
import com.example.bitclient.ui.viewmodels.RepositoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RepositoriesViewModelsModule {

    @ExperimentalPagingApi
    @RepositoriesScope
    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel
}