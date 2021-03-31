package com.example.bitclient.data.repositories.commits

import com.example.bitclient.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitsResponse
import com.example.bitclient.data.network.api.RequestsApi
import javax.inject.Inject

class CommitsRepositoryImpl @Inject constructor(@RequestQualifier private val service: RequestsApi) :
    CommitsRepository {
    override suspend fun retrieveCommits(
        workspaceId: String,
        repositoryId: String,
        branchName: String,
        page: Int
    ): CommitsResponse = service.getCommits(workspaceId, repositoryId, branchName, page)
}