package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

interface PagingDao<DbDataModel : PaginatedDbModel> {

    @Query("")
    fun getAll(): PagingSource<Int, DbDataModel>

    @Insert
    suspend fun insertAll(vararg listOfData: DbDataModel)

    @Insert
    suspend fun insert(data: DbDataModel)

    @Query("")
    suspend fun clearAll()
}