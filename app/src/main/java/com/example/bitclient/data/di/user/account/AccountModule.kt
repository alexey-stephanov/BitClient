package com.example.bitclient.data.di.user.account

import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.repositories.account.AccountRepository
import com.example.bitclient.data.repositories.account.AccountRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AccountModule {

    @AccountScope
    @Binds
    abstract fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    companion object {

        @AccountScope
        @Provides
        fun provideAccountDao(database: AccountDatabase): AccountDao =
            database.accountDao()
    }
}