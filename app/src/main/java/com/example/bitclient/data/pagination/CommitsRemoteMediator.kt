package com.example.bitclient.data.pagination

import androidx.paging.ExperimentalPagingApi
import com.example.bitclient.data.database.PagingDao
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.CommitModel
import com.example.bitclient.data.network.datamodels.commitsmodel.networkmodels.toCommitDbModel
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse

//@ExperimentalPagingApi
//class CommitsRemoteMediator(
//    dao: PagingDao<CommitDbModel>,
//    retrieveData: suspend (page: Int) -> PaginatedResponse<CommitModel>
//) : PagingRemoteMediator<CommitModel, CommitDbModel>(dao, { page -> retrieveData(page) }) {
//
//    override fun convert(dataModels: List<CommitModel>, page: Int): Array<CommitDbModel> {
//        return dataModels.map { it.toCommitDbModel(page) }.toTypedArray()
//    }
//}