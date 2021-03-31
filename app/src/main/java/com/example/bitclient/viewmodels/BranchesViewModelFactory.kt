package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.BranchDataMapper
import com.example.bitclient.data.repositories.branches.BranchesRepository
import javax.inject.Inject

class BranchesViewModelFactory @Inject constructor(
    private val branchesRepository: BranchesRepository,
    private val database: AccountDatabase,
    private val dataMapper: BranchDataMapper
) {
    @ExperimentalPagingApi
    fun create(workspaceId: String, repositoryId: String): BranchesViewModel =
        BranchesViewModel(branchesRepository, database, dataMapper, workspaceId, repositoryId)
}