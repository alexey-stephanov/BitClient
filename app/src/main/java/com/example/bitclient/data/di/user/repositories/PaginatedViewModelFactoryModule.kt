package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.database.RepositoriesDatabase
import com.example.bitclient.data.di.user.RepositoriesDbQualifier
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import com.example.bitclient.ui.viewmodels.BranchesViewModelFactory
import com.example.bitclient.ui.viewmodels.CommitsViewModelFactory
import com.example.bitclient.ui.viewmodels.RepositoriesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class PaginatedViewModelFactoryModule {

//    @RepositoriesScope
//    @Binds
//    abstract fun bindRepositoriesViewModelFactoryModule(
//        repositoriesViewModelFactory: RepositoriesViewModelFactory
//    ): RepositoriesViewModelFactory
//
//    @RepositoriesScope
//    @Binds
//    abstract fun bindBranchesViewModelFactoryModule(
//        branchesViewModelFactory: BranchesViewModelFactory
//    ): BranchesViewModelFactory
//
//    @RepositoriesScope
//    @Binds
//    abstract fun bindCommitsViewModelFactoryModule(
//        commitsViewModelFactory: CommitsViewModelFactory
//    ): CommitsViewModelFactory

    @RepositoriesScope
    @Provides
    fun provideRepositoriesViewModelFactoryModule(
        repositoriesRepository: RepositoriesRepository,
        @RepositoriesDbQualifier repositoriesDatabase: RepositoriesDatabase
    ): RepositoriesViewModelFactory = RepositoriesViewModelFactory(repositoriesRepository, repositoriesDatabase)

    @RepositoriesScope
    @Provides
    fun provideBranchesViewModelFactoryModule(
        repositoriesRepository: RepositoriesRepository,
        @RepositoriesDbQualifier repositoriesDatabase: RepositoriesDatabase
    ): BranchesViewModelFactory = BranchesViewModelFactory(repositoriesRepository, repositoriesDatabase)

    @RepositoriesScope
    @Provides
    fun provideCommitsViewModelFactoryModule(
        repositoriesRepository: RepositoriesRepository,
        @RepositoriesDbQualifier repositoriesDatabase: RepositoriesDatabase
    ): CommitsViewModelFactory = CommitsViewModelFactory(repositoriesRepository, repositoriesDatabase)
}