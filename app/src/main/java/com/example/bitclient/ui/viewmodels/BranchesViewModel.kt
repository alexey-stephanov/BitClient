package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.BranchesDao
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchModel
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.pagination.BranchesRemoteMediator
import com.example.bitclient.data.pagination.DataRetrieving
import com.example.bitclient.data.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.userrepositories.RepositoriesRepository

@ExperimentalPagingApi
class BranchesViewModel(
    private val repositoriesRepository: RepositoriesRepository,
    private val branchesDao: BranchesDao,
    private val workspaceId: String,
    private val repositoryId: String
) : PaginatedViewModel<BranchModel, BranchDbModel>(), DataRetrieving<BranchModel> {

    override suspend fun retrieveData(page: Int): PaginatedResponse<BranchModel> {
        return repositoriesRepository.retrieveRepositoryBranches(workspaceId, repositoryId, page)
    }

    override val remoteMediator: PagingRemoteMediator<BranchModel, BranchDbModel> =
        BranchesRemoteMediator(branchesDao) { page -> retrieveData(page) }

    override fun getPagingSource(): PagingSource<Int, BranchDbModel> =
        branchesDao.getAll()
}