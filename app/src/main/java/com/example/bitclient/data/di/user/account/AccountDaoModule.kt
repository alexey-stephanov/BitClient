package com.example.bitclient.data.di.user.account

import com.example.bitclient.data.database.UserDatabase
import com.example.bitclient.data.database.UserInfoDao
import dagger.Module
import dagger.Provides

@Module
class AccountDaoModule {

    @AccountScope
    @Provides
    fun provideUserInfoDao(userDatabase: UserDatabase): UserInfoDao =
        userDatabase.userInfoDao()
}