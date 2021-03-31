package com.example.bitclient.data.repositories.branches

import com.example.bitclient.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchesResponse
import com.example.bitclient.data.network.api.RequestsApi
import javax.inject.Inject

class BranchesRepositoryImpl @Inject constructor(@RequestQualifier private val service: RequestsApi) :
    BranchesRepository {
    override suspend fun retrieveBranches(
        workspaceId: String,
        repositoryId: String,
        page: Int
    ): BranchesResponse = service.getBranches(workspaceId, repositoryId, page)
}