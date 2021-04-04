package com.example.bitclient.di.user.repositories.branches

import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.BranchesDao
import com.example.bitclient.data.network.datamappers.BranchDataMapper
import com.example.bitclient.data.repositories.branches.BranchesRepository
import com.example.bitclient.data.repositories.branches.BranchesRepositoryImpl
import com.example.bitclient.viewmodels.factories.BranchesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface BranchesModule {

    @BranchesScope
    @Binds
    fun bindBranchesRepository(branchesRepositoryImpl: BranchesRepositoryImpl): BranchesRepository

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