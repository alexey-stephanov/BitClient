package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.database.UserDatabase
import com.example.bitclient.data.database.UserRepositoriesDao
import com.example.bitclient.data.repositories.userrepositories.UserRepositoriesRepository
import com.example.bitclient.data.repositories.userrepositories.UserRepositoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoriesModule {

    @RepositoriesScope
    @Binds
    abstract fun bindUserRepositoriesRepository(userRepositoriesRepositoryImpl: UserRepositoriesRepositoryImpl): UserRepositoriesRepository

    companion object {
        @RepositoriesScope
        @Provides
        fun provideRepositoriesDao(userDatabase: UserDatabase): UserRepositoriesDao =
            userDatabase.userRepositoriesDao()
    }
}