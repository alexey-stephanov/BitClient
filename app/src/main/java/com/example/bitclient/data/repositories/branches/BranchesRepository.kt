package com.example.bitclient.data.repositories.branches

import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchesResponse

interface BranchesRepository {
    suspend fun retrieveBranches(
        workspaceId: String,
        repositoryId: String,
        page: Int
    ): BranchesResponse
}