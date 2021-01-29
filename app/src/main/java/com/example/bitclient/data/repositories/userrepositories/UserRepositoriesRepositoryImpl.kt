package com.example.bitclient.data.repositories.userrepositories

import com.example.bitclient.data.database.UserRepositoriesDao
import com.example.bitclient.data.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchesResponse
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitsResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse
import com.example.bitclient.data.network.requests.RequestsApi
import javax.inject.Inject

class UserRepositoriesRepositoryImpl @Inject constructor(@RequestQualifier private val service: RequestsApi, private val userRepositoriesDao: UserRepositoriesDao) :
    UserRepositoriesRepository {

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