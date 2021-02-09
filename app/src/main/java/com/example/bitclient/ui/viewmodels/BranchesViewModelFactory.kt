package com.example.bitclient.ui.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchDataMapper
import com.example.bitclient.data.repositories.accountrepositories.RepositoriesRepository
import javax.inject.Inject

class BranchesViewModelFactory @Inject constructor(
    private val repository: RepositoriesRepository,
    private val database: AccountDatabase,
    private val dataMapper: BranchDataMapper
) {
    @ExperimentalPagingApi
    fun create(workspaceId: String, repositoryId: String): BranchesViewModel =
        BranchesViewModel(repository, database, dataMapper, workspaceId, repositoryId)
}