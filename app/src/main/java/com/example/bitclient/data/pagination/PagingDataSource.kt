package com.example.bitclient.data.pagination

import androidx.paging.PagingSource
import com.example.bitclient.data.network.datamodels.PaginatedResponse

class PagingDataSource<DataModel : Any>(
    private val retrieveData: suspend (page: Int) -> PaginatedResponse<DataModel>
) : PagingSource<Int, DataModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataModel> {
        return try {
            val page = params.key ?: 1

            val response = retrieveData(page)

            return LoadResult.Page(
                data = response.values,
                prevKey = null,
                nextKey = if (response.nextPage != null) page + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}