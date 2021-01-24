package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.database.UserDatabase
import com.example.bitclient.data.database.UserRepositoriesDao
import dagger.Module
import dagger.Provides

@Module
class RepositoriesDaoModule {

    @RepositoriesScope
    @Provides
    fun provideRepositoriesDao(userDatabase: UserDatabase): UserRepositoriesDao =
        userDatabase.userRepositoriesDao()
}