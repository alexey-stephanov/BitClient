package com.example.bitclient.data.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.bitclient.data.database.RemoteKeysDao
import com.example.bitclient.data.database.UserRepositoriesDao
import com.example.bitclient.data.network.datamodels.RemoteKeys
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.requests.RequestsApi
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class UserRepositoriesRemoteMediator(
    private val service: RequestsApi,
    private val userRepositoriesDao: UserRepositoriesDao
) : RemoteMediator<Int, UserModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserModel>
    ): MediatorResult {
        val page = when(loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.accountId
            }
        }

        try {
            val response = service.getRepositories("", page)
            if (loadType == LoadType.REFRESH) {
                userRepositoriesDao.clearAll()
            }
            userRepositoriesDao.insertAllRepositories(response.values)
            return MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}