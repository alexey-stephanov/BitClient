package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.network.authorization.AuthorizationScope
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