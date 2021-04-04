package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.BranchDataMapper
import com.example.bitclient.data.network.datamappers.WorkspaceDataMapper
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchModel
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.WorkspaceModel
import com.example.bitclient.data.repositories.branches.BranchesRepository
import com.example.bitclient.data.repositories.organizations.OrganizationsRepository
import com.example.bitclient.pagination.PagingRemoteMediator

@ExperimentalPagingApi
class OrganizationsViewModel(
    private val organizationsRepository: OrganizationsRepository,
    database: AccountDatabase,
    dataMapper: WorkspaceDataMapper,
    private val accountId: String
) : PaginatedViewModel<WorkspaceModel, WorkspaceDbModel>() {

    private val workspacesDao = database.workspacesDao()

    override val remoteMediator: PagingRemoteMediator<WorkspaceModel, WorkspaceDbModel> =
        PagingRemoteMediator(workspacesDao, database, dataMapper, accountId) { page -> retrieveData(page) }

    private suspend fun retrieveData(page: Int): PaginatedResponse<WorkspaceModel> {
        return organizationsRepository.retrieveWorkspaces(page)
    }

    override fun getPagingSource(): PagingSource<Int, WorkspaceDbModel> =
        workspacesDao.getItemsByOwnerId(accountId)
}