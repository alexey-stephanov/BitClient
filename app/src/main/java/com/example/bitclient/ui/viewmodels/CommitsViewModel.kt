package com.example.bitclient.ui.viewmodels

import com.example.bitclient.data.network.datamodels.PaginatedResponse
import com.example.bitclient.data.network.datamodels.commitsmodel.CommitModel
import com.example.bitclient.data.repositories.userrepositories.UserRepositoriesRepository

class CommitsViewModel(
    private val userRepositoriesRepository: UserRepositoriesRepository,
    private val workspaceId: String,
    private val repositoryId: String,
    private val branchName: String
) : PaginatedViewModel<CommitModel>() {
    override suspend fun retrieveData(page: Int): PaginatedResponse<CommitModel> {
        return userRepositoriesRepository.retrieveBranchCommits(workspaceId, repositoryId, branchName, page)
    }
}