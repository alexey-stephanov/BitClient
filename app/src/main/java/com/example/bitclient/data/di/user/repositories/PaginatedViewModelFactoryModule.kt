package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import com.example.bitclient.ui.viewmodels.BranchesViewModelFactory
import com.example.bitclient.ui.viewmodels.CommitsViewModelFactory
import com.example.bitclient.ui.viewmodels.RepositoriesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PaginatedViewModelFactoryModule {

    @RepositoriesScope
    @Provides
    fun provideRepositoriesViewModelFactoryModule(
        repository: RepositoriesRepository,
        database: AccountDatabase
    ): RepositoriesViewModelFactory = RepositoriesViewModelFactory(repository, database)

    @RepositoriesScope
    @Provides
    fun provideBranchesViewModelFactoryModule(
        repository: RepositoriesRepository,
        database: AccountDatabase
    ): BranchesViewModelFactory = BranchesViewModelFactory(repository, database)

    @RepositoriesScope
    @Provides
    fun provideCommitsViewModelFactoryModule(
        repository: RepositoriesRepository,
        database: AccountDatabase
    ): CommitsViewModelFactory = CommitsViewModelFactory(repository, database)
}