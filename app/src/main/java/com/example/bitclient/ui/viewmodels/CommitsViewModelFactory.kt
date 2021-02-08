package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.RepositoriesDatabase
import com.example.bitclient.data.di.user.RepositoriesDbQualifier
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import javax.inject.Inject

class CommitsViewModelFactory @Inject constructor(
    private val repository: RepositoriesRepository,
    @RepositoriesDbQualifier private val database: RepositoriesDatabase
) {
    @ExperimentalPagingApi
    fun create(workspaceId: String, repositoryId: String, branchName: String): CommitsViewModel =
        CommitsViewModel(repository, database, workspaceId, repositoryId, branchName)
}