package com.example.bitclient.data.di.user

import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.di.user.account.AccountScope
import com.example.bitclient.data.repositories.account.AccountRepository
import com.example.bitclient.data.repositories.account.AccountRepositoryImpl
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class UserModule {

    @UserScope
    @Binds
    abstract fun bindUserManager(userManagerImpl: UserManagerImpl): UserManager

    @UserScope
    @Binds
    abstract fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    companion object {

        @UserScope
        @Provides
        fun provideAccountDao(database: AccountDatabase): AccountDao =
            database.accountDao()
    }
}