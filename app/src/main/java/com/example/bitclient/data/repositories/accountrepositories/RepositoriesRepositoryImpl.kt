package com.example.bitclient.data.repositories.accountrepositories

import com.example.bitclient.data.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchesResponse
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitsResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse
import com.example.bitclient.data.network.requests.RequestsApi
import javax.inject.Inject

class RepositoriesRepositoryImpl @Inject constructor(@RequestQualifier private val service: RequestsApi) :
    RepositoriesRepository {

    override suspend fun retrieveUserWorkspaces(): WorkspacesResponse = service.getWorkspaces()

    override suspend fun retrieveUserRepositories(
        workspaceId: String,
        page: Int
    ): RepositoriesResponse =
        service.getRepositories(workspaceId, page)

    override suspend fun retrieveRepositoryBranches(
        workspaceId: String,
        repositoryId: String,
        page: Int
    ): BranchesResponse = service.getBranches(workspaceId, repositoryId, page)

    override suspend fun retrieveBranchCommits(
        workspaceId: String,
        repositoryId: String,
        branchName: String,
        page: Int
    ): CommitsResponse = service.getCommits(workspaceId, repositoryId, branchName, page)
}