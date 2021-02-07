package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel

@Dao
abstract class RepositoriesDao : PagingDao<RepositoryDbModel> {

    @Query("SELECT * FROM repositories")
    abstract override fun getAll(): PagingSource<Int, RepositoryDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override suspend fun insertAll(vararg listOfData: RepositoryDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override suspend fun insert(data: RepositoryDbModel)

    @Query("DELETE FROM repositories")
    abstract override suspend fun clearAll()
}