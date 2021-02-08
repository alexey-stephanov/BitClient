package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel

@Dao
abstract class BranchesDao : PagingDao<BranchDbModel> {

    @Query("SELECT * FROM branches")
    abstract override fun getAll(): PagingSource<Int, BranchDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override suspend fun insertAll(vararg listOfData: BranchDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override suspend fun insert(data: BranchDbModel)

    @Query("DELETE FROM branches")
    abstract override suspend fun clearAll()
}