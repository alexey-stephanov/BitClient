package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.network.datamodels.branchesmodel.BranchDataMapper
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitDataMapper
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryDataMapper
import dagger.Module
import dagger.Provides

@Module
class MappersModule {

    @RepositoriesScope
    @Provides
    fun provideRepositoryDataMapper(): RepositoryDataMapper =
        RepositoryDataMapper()

    @RepositoriesScope
    @Provides
    fun provideBranchDataMapper(): BranchDataMapper =
        BranchDataMapper()

    @RepositoriesScope
    @Provides
    fun provideCommitDataMapper(): CommitDataMapper =
        CommitDataMapper()
}