package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel

@Dao
abstract class RepositoriesDao : PagingDao<RepositoryDbModel> {

    @Query("SELECT * FROM repositories WHERE repository_owner_id = :ownerId")
    abstract override fun getAll(ownerId: String): PagingSource<Int, RepositoryDbModel>

    @Query("DELETE FROM repositories")
    abstract override suspend fun clearAll()
}