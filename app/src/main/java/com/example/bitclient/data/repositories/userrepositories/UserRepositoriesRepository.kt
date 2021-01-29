package com.example.bitclient.data.repositories.userrepositories

import com.example.bitclient.data.network.datamodels.branchesmodel.BranchesResponse
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitsResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse

interface UserRepositoriesRepository {
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