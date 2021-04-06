package com.example.bitclient.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.bitclient.data.database.AccountDatabase
import com.example.bitclient.data.database.PagingDao
import com.example.bitclient.data.network.datamappers.NetworkToDbDataMapper
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedResponse
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PagingRemoteMediator<DataModel, DbDataModel : PaginatedDbModel>(
    private val dao: PagingDao<DbDataModel>,
    private val database: AccountDatabase,
    private val dataMapper: NetworkToDbDataMapper<DataModel, DbDataModel>,
    private val ownerId: String,
    private val retrieveData: suspend (page: Int) -> PaginatedResponse<DataModel>
) : RemoteMediator<Int, DbDataModel>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, DbDataModel>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> state.lastItemOrNull()?.page?.minus(1) ?: 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                lastItem.page + 1
            }
        }

        return try {
            val data = retrieveData(page).values
            val isEndOfList = data.isEmpty()
            if (!isEndOfList) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        dao.clearItemsByOwnerId(ownerId)
                    }
                    val dbModel = data.map { data ->
                        dataMapper.convert(data, page, ownerId)
                    }
                    dao.insertAll(dbModel)
                }
            }
            MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }
}