package com.example.bitclient.data.pagination

import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse

interface DataRetrieving<DataModel : Any> {
    suspend fun retrieveData(page: Int) : PaginatedResponse<DataModel>
}