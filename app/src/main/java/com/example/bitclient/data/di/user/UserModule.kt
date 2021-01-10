package com.example.bitclient.data.di.user

import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserManagerImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {

    @UserScope
    @Binds
    abstract fun bindUserManager(userManagerImpl: UserManagerImpl): UserManager
}