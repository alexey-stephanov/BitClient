package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.BranchDataMapper
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchModel
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse
import com.example.bitclient.data.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.branches.BranchesRepository

@ExperimentalPagingApi
class BranchesViewModel(
    private val branchesRepository: BranchesRepository,
    database: AccountDatabase,
    dataMapper: BranchDataMapper,
    private val workspaceId: String,
    private val repositoryId: String
) : PaginatedViewModel<BranchModel, BranchDbModel>() {

    private val branchesDao = database.branchesDao()

    override val remoteMediator: PagingRemoteMediator<BranchModel, BranchDbModel> =
        PagingRemoteMediator(branchesDao, database, dataMapper) { page -> retrieveData(page) }

    private suspend fun retrieveData(page: Int): PaginatedResponse<BranchModel> {
        return branchesRepository.retrieveBranches(workspaceId, repositoryId, page)
    }

    override fun getPagingSource(): PagingSource<Int, BranchDbModel> =
        branchesDao.getAll("")
}