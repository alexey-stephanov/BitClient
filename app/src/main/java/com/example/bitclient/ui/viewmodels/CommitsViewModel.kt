package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitDataMapper
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitModel
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.pagination.DataRetrieving
import com.example.bitclient.data.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.commits.CommitsRepository
import com.example.bitclient.data.repositories.repositories.RepositoriesRepository

@ExperimentalPagingApi
class CommitsViewModel(
    private val commitsRepository: CommitsRepository,
    database: AccountDatabase,
    dataMapper: CommitDataMapper,
    private val workspaceId: String,
    private val repositoryId: String,
    private val branchName: String
) : PaginatedViewModel<CommitModel, CommitDbModel>(), DataRetrieving<CommitModel> {

    private val commitsDao = database.commitsDao()

    override val remoteMediator: PagingRemoteMediator<CommitModel, CommitDbModel> =
        PagingRemoteMediator(commitsDao, database, dataMapper) { page -> retrieveData(page) }

    override suspend fun retrieveData(page: Int): PaginatedResponse<CommitModel> {
        return commitsRepository.retrieveCommits(
            workspaceId,
            repositoryId,
            branchName,
            page
        )
    }

    override fun getPagingSource(): PagingSource<Int, CommitDbModel> =
        commitsDao.getAll("")
}