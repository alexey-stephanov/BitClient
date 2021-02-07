package com.example.bitclient.data.di.user.account

import android.content.Context
import androidx.room.Room
import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.di.user.AccountDbQualifier
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
        fun provideAccountDao(@AccountDbQualifier accountDatabase: AccountDatabase): AccountDao =
            accountDatabase.accountDao()
    }
}