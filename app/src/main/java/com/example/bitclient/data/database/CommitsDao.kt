package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel

@Dao
interface CommitsDao : PagingDao<CommitDbModel> {

    @Query("SELECT * FROM commits WHERE commit_owner_id = :ownerId")
    override fun getItemsByOwnerId(ownerId: String): PagingSource<Int, CommitDbModel>

    @Query("DELETE FROM commits WHERE commit_owner_id = :ownerId")
    override suspend fun clearItemsByOwnerId(ownerId: String)

    @Query("DELETE FROM commits")
    override suspend fun clearAll()
}