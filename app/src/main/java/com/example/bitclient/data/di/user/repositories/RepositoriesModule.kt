package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.RepositoriesDao
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryDataMapper
import com.example.bitclient.data.repositories.account.AccountRepository
import com.example.bitclient.data.repositories.repositories.RepositoriesRepository
import com.example.bitclient.data.repositories.repositories.RepositoriesRepositoryImpl
import com.example.bitclient.ui.viewmodels.RepositoriesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoriesModule {

    @RepositoriesScope
    @Binds
    abstract fun bindRepositoriesRepository(repositoriesRepositoryImpl: RepositoriesRepositoryImpl): RepositoriesRepository

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
        fun provideRepositoriesViewModelFactoryModule(
            repositoriesRepository: RepositoriesRepository,
            database: AccountDatabase,
            dataMapper: RepositoryDataMapper
        ): RepositoriesViewModelFactory = RepositoriesViewModelFactory(repositoriesRepository, database, dataMapper)
    }
}