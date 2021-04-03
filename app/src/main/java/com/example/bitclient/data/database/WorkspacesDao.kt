package com.example.bitclient.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel

@Dao
interface WorkspacesDao : PagingDao<WorkspaceDbModel>{

    @Query("SELECT * FROM workspaces WHERE workspace_owner_id = :ownerId")
    override fun getItemsByOwnerId(ownerId: String): PagingSource<Int, WorkspaceDbModel>

    @Query("DELETE FROM workspaces WHERE workspace_owner_id = :ownerId")
    override suspend fun clearItemsByOwnerId(ownerId: String)

    @Query("DELETE FROM workspaces")
    override suspend fun clearAll()
}