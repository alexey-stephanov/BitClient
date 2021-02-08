package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import javax.inject.Inject

class CommitsViewModelFactory @Inject constructor(
    private val repository: RepositoriesRepository,
    private val database: AccountDatabase
) {
    @ExperimentalPagingApi
    fun create(workspaceId: String, repositoryId: String, branchName: String): CommitsViewModel =
        CommitsViewModel(repository, database, workspaceId, repositoryId, branchName)
}