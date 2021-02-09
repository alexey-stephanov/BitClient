package com.example.bitclient.data.network.datamodels

import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

interface NetworkToDbDataMapper<DataModel, DbDataModel : PaginatedDbModel> {
    fun convert(dataModel: DataModel, page: Int): DbDataModel
}