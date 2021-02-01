package com.example.bitclient.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryModel

@Dao
interface UserRepositoriesDao {

    @Query("SELECT * FROM repositories")
    fun getAllRepositories(): List<RepositoryModel>

    @Query("SELECT * FROM repositories WHERE repositoryId = :repositoryId")
    fun getRepositoryById(repositoryId: String): RepositoryModel

    @Insert
    suspend fun insertAllRepositories(repositories: List<RepositoryModel>)

    @Insert
    suspend fun insertRepository(repository: RepositoryModel)

    @Query("DELETE FROM repositories")
    suspend fun clearAll()
}