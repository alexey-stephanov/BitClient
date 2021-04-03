package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel

@Dao
interface BranchesDao : PagingDao<BranchDbModel> {

    @Query("SELECT * FROM branches WHERE branch_owner_id = :ownerId")
    override fun getItemsByOwnerId(ownerId: String): PagingSource<Int, BranchDbModel>

    @Query("DELETE FROM branches WHERE branch_owner_id = :ownerId")
    override suspend fun clearItemsByOwnerId(ownerId: String)

    @Query("DELETE FROM branches")
    override suspend fun clearAll()
}