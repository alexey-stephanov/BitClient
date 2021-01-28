package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.datamodels.TokensModel
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchesResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse

interface UserDataRepository {
    suspend fun refreshAccessToken(): TokensModel
    suspend fun retrieveUserInfo(): UserModel
    suspend fun retrieveUserWorkspaces(): WorkspacesResponse
    suspend fun retrieveUserRepositories(workspaceId: String, page: Int): RepositoriesResponse
    suspend fun retrieveRepositoryBranches(
        workspaceId: String,
        repositoryId: String,
        page: Int
    ): BranchesResponse
}