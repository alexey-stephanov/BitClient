package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

interface PagingDao<DbDataModel : PaginatedDbModel> {

    @Query("")
    fun getAll(): PagingSource<Int, DbDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listOfData: List<DbDataModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: DbDataModel)

    @Query("")
    suspend fun clearAll()
}