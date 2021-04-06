package com.example.bitclient.data.repositories.repositories

import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoriesResponse

interface RepositoriesRepository {
    suspend fun retrieveUserRepositories(workspaceId: String, page: Int): RepositoriesResponse
}