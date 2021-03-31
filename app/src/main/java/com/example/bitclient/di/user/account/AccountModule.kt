package com.example.bitclient.di.user.account

import androidx.lifecycle.ViewModel
import com.example.bitclient.di.ViewModelKey
import com.example.bitclient.viewmodels.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AccountModule {
    @AccountScope
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel
}