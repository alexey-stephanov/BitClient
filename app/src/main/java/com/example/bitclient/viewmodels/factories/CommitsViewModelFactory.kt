package com.example.bitclient.viewmodels.factories

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.CommitDataMapper
import com.example.bitclient.data.repositories.commits.CommitsRepository
import com.example.bitclient.viewmodels.CommitsViewModel
import javax.inject.Inject

class CommitsViewModelFactory @Inject constructor(
    private val commitsRepository: CommitsRepository,
    private val database: AccountDatabase,
    private val dataMapper: CommitDataMapper
) {
    @ExperimentalPagingApi
    fun create(workspaceId: String, repositoryId: String, branchName: String): CommitsViewModel =
        CommitsViewModel(
            commitsRepository,
            database,
            dataMapper,
            workspaceId,
            repositoryId,
            branchName
        )
}