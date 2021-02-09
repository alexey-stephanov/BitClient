package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitDataMapper
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import javax.inject.Inject

class CommitsViewModelFactory @Inject constructor(
    private val repository: RepositoriesRepository,
    private val database: AccountDatabase,
    private val dataMapper: CommitDataMapper
) {
    @ExperimentalPagingApi
    fun create(workspaceId: String, repositoryId: String, branchName: String): CommitsViewModel =
        CommitsViewModel(repository, database, dataMapper, workspaceId, repositoryId, branchName)
}