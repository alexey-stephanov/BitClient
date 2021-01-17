package com.example.bitclient.data.pagination

import android.util.Log
import androidx.paging.PagingSource
import com.example.bitclient.data.network.networkmodels.PaginatedResponse

class PagingDataSource<T : Any>(
    private val retrieveData: suspend (page: Int) -> PaginatedResponse<T>
) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val page = params.key ?: 1

            val response = retrieveData(page)

            return LoadResult.Page(
                data = response.values,
                prevKey = null,
                nextKey = if (response.nextPage != null) page + 1 else null
            )

        } catch (e: Exception) {
            Log.e("ASD",e.toString())
            LoadResult.Error(e)
        }
    }
}