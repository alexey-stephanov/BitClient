package com.example.bitclient.data.pagination

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.PagingDao
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.RepositoryModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.networkmodels.toRepositoryDbModel

@ExperimentalPagingApi
class RepositoriesRemoteMediator(
    dao: PagingDao<RepositoryDbModel>,
    retrieveData: suspend (page: Int) -> PaginatedResponse<RepositoryModel>
) : PagingRemoteMediator<RepositoryModel, RepositoryDbModel>(dao, { page -> retrieveData(page) }) {

    override fun convert(dataModels: List<RepositoryModel>, page: Int): Array<RepositoryDbModel> {
        return dataModels.map { it.toRepositoryDbModel(page) }.toTypedArray()
    }
}