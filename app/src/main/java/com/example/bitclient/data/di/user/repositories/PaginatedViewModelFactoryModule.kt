package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchDataMapper
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitDataMapper
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryDataMapper
import com.example.bitclient.data.repositories.account.AccountRepository
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
        repositoriesRepository: RepositoriesRepository,
        accountRepository: AccountRepository,
        database: AccountDatabase,
        dataMapper: RepositoryDataMapper
    ): RepositoriesViewModelFactory = RepositoriesViewModelFactory(repositoriesRepository, accountRepository, database, dataMapper)

    @RepositoriesScope
    @Provides
    fun provideBranchesViewModelFactoryModule(
        repository: RepositoriesRepository,
        database: AccountDatabase,
        dataMapper: BranchDataMapper
    ): BranchesViewModelFactory = BranchesViewModelFactory(repository, database, dataMapper)

    @RepositoriesScope
    @Provides
    fun provideCommitsViewModelFactoryModule(
        repository: RepositoriesRepository,
        database: AccountDatabase,
        dataMapper: CommitDataMapper
    ): CommitsViewModelFactory = CommitsViewModelFactory(repository, database, dataMapper)
}