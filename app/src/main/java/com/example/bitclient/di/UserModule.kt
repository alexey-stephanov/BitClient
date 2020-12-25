package com.example.bitclient.di

import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UserModule {

    @Singleton
    @Binds
    abstract fun bindUserManager(userManagerImpl: UserManagerImpl): UserManager
}