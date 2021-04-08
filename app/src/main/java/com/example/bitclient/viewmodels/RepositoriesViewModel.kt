package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.RepositoryDataMapper
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.repositories.repositories.RepositoriesRepository

@ExperimentalPagingApi
class RepositoriesViewModel(
    private val repositoriesRepository: RepositoriesRepository,
    database: AccountDatabase,
    dataMapper: RepositoryDataMapper,
    workspaceId: String
) : PaginatedViewModel<RepositoryModel, RepositoryDbModel>(
    database.repositoriesDao(),
    database,
    dataMapper,
    workspaceId,
    { database.repositoriesDao().getItemsByOwnerId(workspaceId) },
    { repositoriesRepository.retrieveUserRepositories(workspaceId, it) }
)