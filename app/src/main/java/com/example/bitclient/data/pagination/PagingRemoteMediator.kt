package com.example.bitclient.data.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.bitclient.data.database.PagingDao
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedResponse
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
abstract class PagingRemoteMediator<DataModel : Any, DbDataModel : PaginatedDbModel>(
    private val dao: PagingDao<DbDataModel>,
    private val retrieveData: suspend (page: Int) -> PaginatedResponse<DataModel>
) : RemoteMediator<Int, DbDataModel>() {

    abstract fun convert(dataModels: List<DataModel>, page: Int): Array<DbDataModel>

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
            if (!NetworkStatus.isNetworkAvailable()) {
                dao.getAll()
            }
            val networkModel = retrieveData(page).values
            val isEndOfList = networkModel.isEmpty()
            val dbModel = convert(networkModel, page)
            if (loadType == LoadType.REFRESH) {
                dao.clearAll()
            }
            dao.insertAll(*dbModel)
            MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }
}