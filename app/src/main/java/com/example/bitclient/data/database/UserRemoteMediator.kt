package com.example.bitclient.data.database

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.requests.RequestsApi
import okio.IOException

@ExperimentalPagingApi
class UserRemoteMediator(private val database: UserDatabase, private val service: RequestsApi, private val userRepositoriesDao: UserRepositoriesDao) : RemoteMediator<Int, UserModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserModel>
    ): MediatorResult {
        try {
            val loadkey = when(loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    lastItem.id
                }
            }

            val response = service.getRepositories()
            database.withTransaction {
                if(loadType == LoadType.REFRESH)
                    userRepositoriesDao.clearAll()
                userRepositoriesDao.insertAllRepositories(response.values)
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}