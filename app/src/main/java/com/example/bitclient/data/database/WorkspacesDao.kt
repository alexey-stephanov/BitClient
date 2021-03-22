package com.example.bitclient.data.database

import androidx.room.Dao
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel

@Dao
abstract class WorkspacesDao {

    @Query("SELECT * FROM workspaces WHERE workspace_owner_id = :ownerId")
    abstract fun getAll(ownerId: String): List<WorkspaceDbModel>

    @Query("DELETE FROM workspaces")
    abstract suspend fun clearAll()
}