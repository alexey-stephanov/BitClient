package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel
import com.example.bitclient.data.network.networkmodels.workspacesmodel.WorkspacesResponse

interface UserDataRepository {
    suspend fun retrieveUserInfo(): UserModel
    suspend fun retrieveUserRepositories(workspaceId: String, page: Int): RepositoriesResponse
    suspend fun retrieveUserWorkspaces(): WorkspacesResponse
}