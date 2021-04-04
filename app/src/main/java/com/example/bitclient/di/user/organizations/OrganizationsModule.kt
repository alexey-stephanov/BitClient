package com.example.bitclient.di.user.organizations

import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.WorkspacesDao
import com.example.bitclient.data.network.datamappers.WorkspaceDataMapper
import com.example.bitclient.data.repositories.organizations.OrganizationsRepository
import com.example.bitclient.data.repositories.organizations.OrganizationsRepositoryImpl
import com.example.bitclient.viewmodels.factories.OrganizationsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface OrganizationsModule {

    @OrganizationsScope
    @Binds
    fun bindOrganizationsRepository(organizationsRepositoryImpl: OrganizationsRepositoryImpl): OrganizationsRepository

    companion object {

        @OrganizationsScope
        @Provides
        fun provideWorkspacesDao(database: AccountDatabase): WorkspacesDao =
            database.workspacesDao()

        @OrganizationsScope
        @Provides
        fun provideWorkspaceDataMapper(): WorkspaceDataMapper =
            WorkspaceDataMapper()

        @OrganizationsScope
        @Provides
        fun provideOrganizationsViewModelFactoryModule(
            organizationsRepository: OrganizationsRepository,
            database: AccountDatabase,
            dataMapper: WorkspaceDataMapper
        ): OrganizationsViewModelFactory =
            OrganizationsViewModelFactory(organizationsRepository, database, dataMapper)
    }
}