package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchDataMapper
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchModel
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.pagination.DataRetrieving
import com.example.bitclient.data.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository

@ExperimentalPagingApi
class BranchesViewModel(
    private val repository: RepositoriesRepository,
    database: AccountDatabase,
    dataMapper: BranchDataMapper,
    private val workspaceId: String,
    private val repositoryId: String
) : PaginatedViewModel<BranchModel, BranchDbModel>(), DataRetrieving<BranchModel> {

    private val branchesDao = database.branchesDao()

    override suspend fun retrieveData(page: Int): PaginatedResponse<BranchModel> {
        return repository.retrieveRepositoryBranches(workspaceId, repositoryId, page)
    }

    override val remoteMediator: PagingRemoteMediator<BranchModel, BranchDbModel> =
        PagingRemoteMediator(branchesDao, database, dataMapper) { page -> retrieveData(page) }

    override fun getPagingSource(): PagingSource<Int, BranchDbModel> =
        branchesDao.getAll()
}