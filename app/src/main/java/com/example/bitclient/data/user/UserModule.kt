package com.example.bitclient.data.user

import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {

    @UserScope
    @Binds
    abstract fun bindUserManager(userManagerImpl: UserManagerImpl): UserManager
}