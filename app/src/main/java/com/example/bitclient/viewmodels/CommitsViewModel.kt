package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.CommitDataMapper
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitModel
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse
import com.example.bitclient.pagination.PagingRemoteMediator
import com.example.bitclient.data.repositories.commits.CommitsRepository

@ExperimentalPagingApi
class CommitsViewModel(
    private val commitsRepository: CommitsRepository,
    database: AccountDatabase,
    dataMapper: CommitDataMapper,
    private val workspaceId: String,
    private val repositoryId: String,
    private val branchName: String
) : PaginatedViewModel<CommitModel, CommitDbModel>() {

    private val commitsDao = database.commitsDao()

    override val remoteMediator: PagingRemoteMediator<CommitModel, CommitDbModel> =
        PagingRemoteMediator(commitsDao, database, dataMapper, branchName + repositoryId) { page -> retrieveData(page) }

    private suspend fun retrieveData(page: Int): PaginatedResponse<CommitModel> {
        return commitsRepository.retrieveCommits(
            workspaceId,
            repositoryId,
            branchName,
            page
        )
    }

    override fun getPagingSource(): PagingSource<Int, CommitDbModel> =
        commitsDao.getItemsByOwnerId(branchName + repositoryId)
}