package com.example.bitclient.data.di.user

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.di.ViewModelKey
import com.example.bitclient.data.di.user.account.AccountScope
import com.example.bitclient.data.repositories.account.AccountRepository
import com.example.bitclient.data.repositories.account.AccountRepositoryImpl
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserManagerImpl
import com.example.bitclient.ui.viewmodels.HomeViewModel
import com.example.bitclient.ui.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class UserModule {

    @UserScope
    @Binds
    abstract fun bindUserManager(userManagerImpl: UserManagerImpl): UserManager

    @UserScope
    @Binds
    abstract fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @UserScope
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    companion object {

        @UserScope
        @Provides
        fun provideAccountDao(database: AccountDatabase): AccountDao =
            database.accountDao()
    }
}