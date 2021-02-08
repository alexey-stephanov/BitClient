package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel

@Dao
interface RepositoriesDao : PagingDao<RepositoryDbModel> {

    @Query("SELECT * FROM repositories")
    override fun getAll(): PagingSource<Int, RepositoryDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertAll(vararg listOfData: RepositoryDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(data: RepositoryDbModel)

    @Query("DELETE FROM repositories")
    override suspend fun clearAll()
}