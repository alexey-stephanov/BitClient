package com.example.bitclient.data.repositories.repositories

import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.WorkspacesResponse

interface RepositoriesRepository {
    suspend fun retrieveUserWorkspaces(): WorkspacesResponse
    suspend fun retrieveUserRepositories(workspaceId: String, page: Int): RepositoriesResponse
}