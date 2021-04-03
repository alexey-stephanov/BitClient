package com.example.bitclient.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.network.datamappers.RepositoryDataMapper
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.repositories.repositories.RepositoriesRepository
import com.example.bitclient.pagination.PagingRemoteMediator
import kotlinx.coroutines.FlowPreview

@ExperimentalPagingApi
class RepositoriesViewModel(
    private val repositoriesRepository: RepositoriesRepository,
    database: AccountDatabase,
    dataMapper: RepositoryDataMapper,
    private val workspaceId: String
) : PaginatedViewModel<RepositoryModel, RepositoryDbModel>() {

    private val repositoriesDao = database.repositoriesDao()

    @FlowPreview
    override val remoteMediator: PagingRemoteMediator<RepositoryModel, RepositoryDbModel> =
        PagingRemoteMediator(
            repositoriesDao,
            database,
            dataMapper,
            workspaceId
        ) { page -> retrieveData(page) }

    @FlowPreview
    suspend fun retrieveData(page: Int): PaginatedResponse<RepositoryModel> =
        repositoriesRepository.retrieveUserRepositories(workspaceId, page)

    override fun getPagingSource(): PagingSource<Int, RepositoryDbModel> =
        repositoriesDao.getItemsByOwnerId(workspaceId)
}