package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel

@Dao
interface CommitsDao : PagingDao<CommitDbModel> {

    @Query("SELECT * FROM commits")
    override fun getAll(): PagingSource<Int, CommitDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAll(vararg listOfData: CommitDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(data: CommitDbModel)

    @Query("DELETE FROM commits")
    override suspend fun clearAll()
}