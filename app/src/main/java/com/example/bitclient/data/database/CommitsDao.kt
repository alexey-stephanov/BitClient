package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel

@Dao
abstract class CommitsDao : PagingDao<CommitDbModel> {

    @Query("SELECT * FROM commits WHERE commit_owner_id = :ownerId")
    abstract override fun getAll(ownerId: String): PagingSource<Int, CommitDbModel>

    @Query("DELETE FROM commits")
    abstract override suspend fun clearAll()
}