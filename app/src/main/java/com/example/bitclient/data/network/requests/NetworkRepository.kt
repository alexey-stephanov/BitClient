package com.example.bitclient.data.network.requests

import com.example.bitclient.data.network.datamodels.TokensModel
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchesResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoriesResponse
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.WorkspacesResponse

interface NetworkRepository {
    suspend fun refreshAccessToken(): TokensModel
}