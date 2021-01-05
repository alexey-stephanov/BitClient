package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.networkmodels.usermodel.UserModel
import javax.inject.Inject

class RequestsDataRepositoryImpl @Inject constructor(private val service: RequestsApi) :
    RequestsDataRepository {

    override suspend fun retrieveUserInfo() : UserModel = service.getUserInfo()

    override suspend fun retrieveUserRepositories(workspaceId: String, page: Int): RepositoriesResponse = service.getRepositories(workspaceId, page)
}