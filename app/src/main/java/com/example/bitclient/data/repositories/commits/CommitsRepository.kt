package com.example.bitclient.data.repositories.commits

import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitsResponse

interface CommitsRepository {
    suspend fun retrieveCommits(
        workspaceId: String,
        repositoryId: String,
        branchName: String,
        page: Int
    ): CommitsResponse
}