package com.example.bitclient.data.di.user.account

import com.example.bitclient.data.database.UserDatabase
import com.example.bitclient.data.database.UserAccountDao
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
        fun provideUserInfoDao(userDatabase: UserDatabase): UserAccountDao =
            userDatabase.userAccountDao()
    }
}