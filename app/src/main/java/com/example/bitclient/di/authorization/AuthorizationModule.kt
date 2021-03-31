package com.example.bitclient.di.authorization

import androidx.lifecycle.ViewModel
import com.example.bitclient.di.ViewModelKey
import com.example.bitclient.viewmodels.AuthorizationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthorizationModule {
    @AuthorizationScope
    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    fun bindAuthorizationViewModel(viewModel: AuthorizationViewModel): ViewModel
}