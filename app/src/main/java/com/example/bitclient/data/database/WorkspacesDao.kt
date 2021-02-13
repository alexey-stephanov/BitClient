package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel

@Dao
abstract class WorkspacesDao : PagingDao<WorkspaceDbModel> {

    @Query("SELECT * FROM workspaces")
    abstract override fun getAll(): PagingSource<Int, WorkspaceDbModel>

    @Query("DELETE FROM workspaces")
    abstract override suspend fun clearAll()
}