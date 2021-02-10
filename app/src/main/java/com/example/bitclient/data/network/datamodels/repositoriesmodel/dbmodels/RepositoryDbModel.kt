package com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

@Entity(tableName = "repositories")
data class RepositoryDbModel(
    @PrimaryKey
    @ColumnInfo(name = "repository_id")
    val repositoryId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "avatar_link")
    val avatarLink: String,
    @ColumnInfo(name = "workspace_id")
    val workspaceId: String,
    @ColumnInfo(name = "is_private")
    val isPrivate: Boolean,
    @ColumnInfo(name = "page")
    override val page: Int
) : PaginatedDbModel {
    override val unique: String
        get() = repositoryId
}