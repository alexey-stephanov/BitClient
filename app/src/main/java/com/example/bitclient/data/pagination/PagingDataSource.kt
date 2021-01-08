package com.example.bitclient.data.pagination

import androidx.paging.PagingSource
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.requests.RequestsDataRepository
import javax.inject.Inject

class PagingDataSource @Inject constructor(
    private val requestsDataRepository: RequestsDataRepository,
    private val workspaceId: String
) : PagingSource<Int, RepositoryModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        return try {
            val nextPage = params.key ?: 1
            val response = requestsDataRepository.retrieveUserRepositories(workspaceId, nextPage)

            LoadResult.Page(
                data = response.values,
                prevKey = null,
                nextKey = if (response.nextPage != null) nextPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}