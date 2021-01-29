package com.example.bitclient.ui.viewmodels

import com.example.bitclient.data.network.datamodels.PaginatedResponse
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchModel
import com.example.bitclient.data.network.requests.NetworkRepository
import com.example.bitclient.data.repositories.userrepositories.UserRepositoriesRepository

class BranchesViewModel(
    private val userRepositoriesRepository: UserRepositoriesRepository,
    private val workspaceId: String,
    private val repositoryId: String
) : PaginatedViewModel<BranchModel>() {

    override suspend fun retrieveData(page: Int): PaginatedResponse<BranchModel> {
        return userRepositoriesRepository.retrieveRepositoryBranches(workspaceId, repositoryId, page)
    }
}