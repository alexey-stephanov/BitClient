package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.CommitDataMapper
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitModel
import com.example.bitclient.data.repositories.commits.CommitsRepository

@ExperimentalPagingApi
class CommitsViewModel(
    private val commitsRepository: CommitsRepository,
    database: AccountDatabase,
    dataMapper: CommitDataMapper,
    private val workspaceId: String,
    private val repositoryId: String,
    private val branchName: String
) : PaginatedViewModel<CommitModel, CommitDbModel>(
    database.commitsDao(),
    database,
    dataMapper,
    branchName,
    { database.commitsDao().getItemsByOwnerId(branchName + repositoryId) },
    { commitsRepository.retrieveCommits(workspaceId, repositoryId, branchName, it) }
)