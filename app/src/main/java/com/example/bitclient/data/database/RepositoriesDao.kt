package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel

@Dao
interface RepositoriesDao : PagingDao<RepositoryDbModel> {

    @Query("SELECT * FROM repositories WHERE repository_owner_id = :ownerId")
    override fun getItemsByOwnerId(ownerId: String): PagingSource<Int, RepositoryDbModel>

    @Query("DELETE FROM repositories WHERE repository_owner_id = :ownerId")
    override suspend fun clearItemsByOwnerId(ownerId: String)
}