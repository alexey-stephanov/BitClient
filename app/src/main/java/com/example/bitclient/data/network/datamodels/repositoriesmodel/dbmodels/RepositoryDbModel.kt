package com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels

import androidx.room.*
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel

@Entity(
    tableName = "repositories",
    foreignKeys = [ForeignKey(
        entity = WorkspaceDbModel::class,
        parentColumns = ["workspace_id"],
        childColumns = ["repository_owner_id"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index(value = ["repository_id"])]
)
data class RepositoryDbModel(
    @PrimaryKey
    @ColumnInfo(name = "repository_id")
    val repositoryId: String,
    @ColumnInfo(name = "repository_owner_id")
    val repositoryOwnerId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "avatar_link")
    val avatarLink: String,
    @ColumnInfo(name = "is_private")
    val isPrivate: Boolean,
    @ColumnInfo(name = "page")
    override val page: Int
) : PaginatedDbModel {
    override val unique: String
        get() = repositoryId
}