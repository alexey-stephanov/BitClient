package com.example.bitclient.data.pagination

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.PagingDao
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.BranchModel
import com.example.bitclient.data.network.datamodels.branchesmodel.networkmodels.toBranchDbModel
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse

@ExperimentalPagingApi
class BranchesRemoteMediator(
    dao: PagingDao<BranchDbModel>,
    retrieveData: suspend (page: Int) -> PaginatedResponse<BranchModel>
) : PagingRemoteMediator<BranchModel, BranchDbModel>(dao, { page -> retrieveData(page) }) {

    override fun convert(dataModels: List<BranchModel>, page: Int): Array<BranchDbModel> {
        return dataModels.map { it.toBranchDbModel(page) }.toTypedArray()
    }
}