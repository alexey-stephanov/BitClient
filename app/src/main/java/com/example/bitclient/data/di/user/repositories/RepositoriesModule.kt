package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.database.BranchesDao
import com.example.bitclient.data.database.CommitsDao
import com.example.bitclient.data.database.RepositoriesDao
import com.example.bitclient.data.database.RepositoriesDatabase
import com.example.bitclient.data.di.user.RepositoriesDbQualifier
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoriesModule {

    @RepositoriesScope
    @Binds
    abstract fun bindUserRepositoriesRepository(userRepositoriesRepositoryImpl: RepositoriesRepositoryImpl): RepositoriesRepository

    companion object {

        @RepositoriesScope
        @Provides
        fun provideRepositoriesDao(@RepositoriesDbQualifier repositoriesDatabase: RepositoriesDatabase): RepositoriesDao =
            repositoriesDatabase.repositoriesDao()

        @RepositoriesScope
        @Provides
        fun provideBranchesDao(@RepositoriesDbQualifier repositoriesDatabase: RepositoriesDatabase): BranchesDao =
            repositoriesDatabase.branchesDao()

        @RepositoriesScope
        @Provides
        fun provideCommitsDao(@RepositoriesDbQualifier repositoriesDatabase: RepositoriesDatabase): CommitsDao =
            repositoriesDatabase.commitsDao()
    }
}