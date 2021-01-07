package com.example.bitclient.data.user.account

import androidx.lifecycle.ViewModel
import com.example.bitclient.ui.viewmodels.AccountViewModel
import com.example.bitclient.ui.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AccountViewModelModule {
    @AccountScope
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel
}