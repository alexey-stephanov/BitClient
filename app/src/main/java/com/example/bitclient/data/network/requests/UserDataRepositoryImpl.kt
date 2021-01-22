package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val service: RequestsApi) :
    UserDataRepository {
    override suspend fun retrieveUserInfo(): UserModel = service.getUserInfo()
    override suspend fun retrieveUserRepositories(
        workspaceId: String,
        page: Int
    ): RepositoriesResponse =
        service.getRepositories(workspaceId, page)

    override suspend fun retrieveUserWorkspaces(): WorkspacesResponse = service.getWorkspaces()
}