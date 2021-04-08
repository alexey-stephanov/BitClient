package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.WorkspaceDataMapper
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.WorkspaceModel
import com.example.bitclient.data.repositories.organizations.OrganizationsRepository

@ExperimentalPagingApi
class OrganizationsViewModel(
    private val organizationsRepository: OrganizationsRepository,
    database: AccountDatabase,
    dataMapper: WorkspaceDataMapper,
    accountId: String
) : PaginatedViewModel<WorkspaceModel, WorkspaceDbModel>(
    database.workspacesDao(),
    database,
    dataMapper,
    accountId,
    { database.workspacesDao().getItemsByOwnerId(accountId) },
    { page -> organizationsRepository.retrieveWorkspaces(page) })