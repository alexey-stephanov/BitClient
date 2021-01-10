package com.example.bitclient.data.di.authorization

import androidx.lifecycle.ViewModel
import com.example.bitclient.ui.viewmodels.AuthorizationViewModel
import com.example.bitclient.data.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthorizationViewModelModule {

    @AuthorizationScope
    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    abstract fun bindAuthorizationViewModel(viewModel: AuthorizationViewModel): ViewModel
}