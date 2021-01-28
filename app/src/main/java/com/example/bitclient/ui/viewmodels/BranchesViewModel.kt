package com.example.bitclient.ui.viewmodels

import com.example.bitclient.data.network.datamodels.PaginatedResponse
import com.example.bitclient.data.network.datamodels.branchesmodel.BranchModel
import com.example.bitclient.data.network.requests.UserDataRepository
import javax.inject.Inject

class BranchesViewModel(
    private val userDataRepository: UserDataRepository,
    private val workspaceId: String,
    private val repositoryId: String
) : PaginatedViewModel<BranchModel>() {

    override suspend fun retrieveData(page: Int): PaginatedResponse<BranchModel> {
        return userDataRepository.retrieveRepositoryBranches(workspaceId, repositoryId, page)
    }
}