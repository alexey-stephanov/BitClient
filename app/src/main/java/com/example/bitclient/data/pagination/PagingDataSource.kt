package com.example.bitclient.data.pagination

import androidx.paging.PagingSource
import com.example.bitclient.data.network.networkmodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.requests.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PagingDataSource @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val workspaceId: Flow<String>
) : PagingSource<Int, RepositoryModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        return try {
            val nextPage = params.key ?: 1
            workspaceId.map {
                userDataRepository.retrieveUserRepositories(it, nextPage)
            }.map { response ->
                LoadResult.Page(
                    data = response.values,
                    prevKey = null,
                    nextKey = if (response.nextPage != null) nextPage + 1 else null
                )
            }.first()
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}