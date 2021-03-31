package com.example.bitclient.di.user.repositories.branches.commits

import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.CommitsDao
import com.example.bitclient.data.network.datamappers.CommitDataMapper
import com.example.bitclient.data.repositories.commits.CommitsRepository
import com.example.bitclient.data.repositories.commits.CommitsRepositoryImpl
import com.example.bitclient.viewmodels.CommitsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface CommitsModule {

    @CommitsScope
    @Binds
    fun bindCommitsRepository(commitsRepositoryImpl: CommitsRepositoryImpl): CommitsRepository

    companion object {

        @CommitsScope
        @Provides
        fun provideCommitsDao(database: AccountDatabase): CommitsDao =
            database.commitsDao()

        @CommitsScope
        @Provides
        fun provideCommitDataMapper(): CommitDataMapper =
            CommitDataMapper()

        @CommitsScope
        @Provides
        fun provideCommitsViewModelFactoryModule(
            commitsRepository: CommitsRepository,
            database: AccountDatabase,
            dataMapper: CommitDataMapper
        ): CommitsViewModelFactory = CommitsViewModelFactory(commitsRepository, database, dataMapper)
    }
}