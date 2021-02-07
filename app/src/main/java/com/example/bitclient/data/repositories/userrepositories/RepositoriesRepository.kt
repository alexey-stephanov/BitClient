package com.example.bitclient.data.repositories.userrepositories

import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchesResponse
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitsResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse

interface RepositoriesRepository {
    suspend fun retrieveUserWorkspaces(): WorkspacesResponse
    suspend fun retrieveUserRepositories(workspaceId: String, page: Int): RepositoriesResponse
    suspend fun retrieveRepositoryBranches(
        workspaceId: String,
        repositoryId: String,
        page: Int
    ): BranchesResponse
    suspend fun retrieveBranchCommits(
        workspaceId: String,
        repositoryId: String,
        branchName: String,
        page: Int
    ): CommitsResponse
}