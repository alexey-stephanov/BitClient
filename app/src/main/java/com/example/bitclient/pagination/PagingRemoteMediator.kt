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
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit

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
            LoadType.REFRESH -> {
                Timber.e("REFRESH")
                state.lastItemOrNull()?.page ?: 1
            }
            LoadType.PREPEND -> {
                Timber.e("PREPEND")
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                Timber.e("${state.lastItemOrNull()}")
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                Timber.e("APPEND")
                lastItem.page + 1
            }
        }

        return try {
            Timber.e(page.toString())
            val data = retrieveData(page)
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.clearItemsByOwnerId(ownerId)
                }
                val dbModel = data.values.map { data ->
                    dataMapper.convert(data, page, ownerId)
                }
                dao.insertAll(dbModel)
            }
            Timber.e((data.nextPage == null).toString())
            MediatorResult.Success(endOfPaginationReached = data.nextPage == null)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }
}