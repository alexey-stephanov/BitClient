package com.example.bitclient.data.di.user.repositories.branches

import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.BranchesDao
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchDataMapper
import com.example.bitclient.data.repositories.branches.BranchesRepository
import com.example.bitclient.data.repositories.branches.BranchesRepositoryImpl
import com.example.bitclient.data.repositories.repositories.RepositoriesRepository
import com.example.bitclient.ui.viewmodels.BranchesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class BranchesModule {

    @BranchesScope
    @Binds
    abstract fun bindBranchesRepository(branchesRepositoryImpl: BranchesRepositoryImpl): BranchesRepository

    companion object {

        @BranchesScope
        @Provides
        fun provideBranchesDao(database: AccountDatabase): BranchesDao =
            database.branchesDao()

        @BranchesScope
        @Provides
        fun provideBranchDataMapper(): BranchDataMapper =
            BranchDataMapper()

        @BranchesScope
        @Provides
        fun provideBranchesViewModelFactoryModule(
            branchesRepository: BranchesRepository,
            database: AccountDatabase,
            dataMapper: BranchDataMapper
        ): BranchesViewModelFactory = BranchesViewModelFactory(branchesRepository, database, dataMapper)
    }
}