package com.example.bitclient.data.pagination

import androidx.paging.PagingSource
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.requests.RequestsDataRepository
import javax.inject.Inject

class RepositoryDataSource @Inject constructor(private val requestsDataRepository: RequestsDataRepository) :
    PagingSource<Int, RepositoryModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        return try {
            val nextPage = params.key ?: 1
            val response = requestsDataRepository.retrieveUserRepositories("", nextPage)

            LoadResult.Page(
                data = response.values,
                prevKey = null,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}