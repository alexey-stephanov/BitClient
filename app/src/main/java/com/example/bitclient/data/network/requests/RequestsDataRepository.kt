package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel

interface RequestsDataRepository {
    suspend fun retrieveUserInfo(): UserModel
    suspend fun retrieveUserRepositories(workspaceId: String, page: Int): RepositoriesResponse
}