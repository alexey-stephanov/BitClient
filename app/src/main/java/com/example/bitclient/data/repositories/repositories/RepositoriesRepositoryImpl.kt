package com.example.bitclient.data.repositories.repositories

import com.example.bitclient.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.workspacesmodel.networkmodels.WorkspacesResponse
import com.example.bitclient.data.network.api.RequestsApi
import javax.inject.Inject

class RepositoriesRepositoryImpl @Inject constructor(@RequestQualifier private val service: RequestsApi) :
    RepositoriesRepository {

    override suspend fun retrieveUserRepositories(
        workspaceId: String,
        page: Int
    ): RepositoriesResponse =
        service.getRepositories(workspaceId, page)
}