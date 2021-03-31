package com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel

@Entity(tableName = "workspaces",
    foreignKeys = [ForeignKey(
        entity = AccountDbModel::class,
        parentColumns = ["account_id"],
        childColumns = ["workspace_owner_id"]
    )])
data class WorkspaceDbModel(
    @PrimaryKey
    @ColumnInfo(name = "workspace_id")
    val workspaceId: String,
    @ColumnInfo(name = "workspace_owner_id")
    val workspaceOwnerId: String,
    @ColumnInfo(name = "workspace_name")
    val workspaceName: String,
    @ColumnInfo(name = "page")
    override val page: Int
) : PaginatedDbModel {
    override val unique: String
        get() = workspaceId
}