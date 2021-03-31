package com.example.bitclient.di.user

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.di.ViewModelKey
import com.example.bitclient.data.repositories.account.AccountRepository
import com.example.bitclient.data.repositories.account.AccountRepositoryImpl
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserManagerImpl
import com.example.bitclient.viewmodels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface UserModule {

    @UserScope
    @Binds
    fun bindUserManager(userManagerImpl: UserManagerImpl): UserManager

    @UserScope
    @Binds
    fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @UserScope
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    companion object {
        @UserScope
        @Provides
        fun provideAccountDao(database: AccountDatabase): AccountDao =
            database.accountDao()
    }
}