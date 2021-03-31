package com.example.bitclient.di.user.repositories

import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.RepositoriesDao
import com.example.bitclient.data.network.datamappers.RepositoryDataMapper
import com.example.bitclient.data.repositories.repositories.RepositoriesRepository
import com.example.bitclient.data.repositories.repositories.RepositoriesRepositoryImpl
import com.example.bitclient.viewmodels.RepositoriesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface RepositoriesModule {

    @RepositoriesScope
    @Binds
    fun bindRepositoriesRepository(repositoriesRepositoryImpl: RepositoriesRepositoryImpl): RepositoriesRepository

    companion object {

        @RepositoriesScope
        @Provides
        fun provideRepositoriesDao(database: AccountDatabase): RepositoriesDao =
            database.repositoriesDao()

        @RepositoriesScope
        @Provides
        fun provideRepositoryDataMapper(): RepositoryDataMapper =
            RepositoryDataMapper()

        @RepositoriesScope
        @Provides
        fun provideRepositoriesViewModelFactory(
            repositoriesRepository: RepositoriesRepository,
            database: AccountDatabase,
            dataMapper: RepositoryDataMapper
        ): RepositoriesViewModelFactory = RepositoriesViewModelFactory(repositoriesRepository, database, dataMapper)
    }
}