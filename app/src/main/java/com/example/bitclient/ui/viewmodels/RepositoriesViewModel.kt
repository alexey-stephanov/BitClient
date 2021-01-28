package com.example.bitclient.ui.viewmodels

import com.example.bitclient.data.database.UserRepositoriesDao
import com.example.bitclient.data.network.datamodels.PaginatedResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.requests.UserDataRepository
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.data.user.UserWorkspacesLiveDataDelegate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userRepositoriesDao: UserRepositoriesDao
) : PaginatedViewModel<RepositoryModel>(), UserWorkspacesLiveDataDelegate {

    override val workspaceIdFlow: Flow<String> = flow {
        val userWorkspaces = userDataRepository.retrieveUserWorkspaces()
        emit(userWorkspaces.workspaces[0].workspaceId)
    }

    override suspend fun retrieveData(page: Int): PaginatedResponse<RepositoryModel> {
        return workspaceIdFlow.map { workspaceId ->
            userDataRepository.retrieveUserRepositories(workspaceId, page)
        }.first()
    }

    fun saveRepositoriesInDatabase(repositories: List<RepositoryModel>) {
        userRepositoriesDao.insertAllRepositories(repositories)
    }
}