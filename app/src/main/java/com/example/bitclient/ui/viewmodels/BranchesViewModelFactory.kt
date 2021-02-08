package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import javax.inject.Inject

class BranchesViewModelFactory @Inject constructor(
    private val repository: RepositoriesRepository,
    private val database: AccountDatabase
) {
    @ExperimentalPagingApi
    fun create(workspaceId: String, repositoryId: String): BranchesViewModel =
        BranchesViewModel(repository, database, workspaceId, repositoryId)
}