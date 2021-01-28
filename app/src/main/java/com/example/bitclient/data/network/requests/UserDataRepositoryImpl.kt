package com.example.bitclient.data.network.requests

import com.example.bitclient.data.di.AuthorizationQualifier
import com.example.bitclient.data.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.TokensModel
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchesResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

private const val GRANT_TYPE = "refresh_token"
private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"

class UserDataRepositoryImpl @Inject constructor(
    @RequestQualifier private val service: RequestsApi,
    @AuthorizationQualifier private val refreshService: RefreshApi,
    private val storage: Storage
) : UserDataRepository {

    override suspend fun refreshAccessToken(): TokensModel {
        val refreshToken = storage.getString(REFRESH_TOKEN_KEY)
        val result = refreshService.refreshAccessToken(GRANT_TYPE, refreshToken)
        storage.setString(ACCESS_TOKEN_KEY, result.accessToken)
        return result
    }

    override suspend fun retrieveUserInfo(): UserModel = service.getUserInfo()

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
}