package com.example.bitclient.data.network.datamappers

import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel

interface NetworkToDbDataMapper<DataModel, DbDataModel : PaginatedDbModel> {
    fun convert(dataModel: DataModel, page: Int, ownerId: String): DbDataModel
}