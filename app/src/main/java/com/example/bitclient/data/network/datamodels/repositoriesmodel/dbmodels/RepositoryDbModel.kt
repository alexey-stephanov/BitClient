package com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

@Entity(tableName = "repositories")
data class RepositoryDbModel(
    @PrimaryKey
    @ColumnInfo(name = "repository_id")
    var repositoryId: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "full_name")
    var fullName: String = "",
    @ColumnInfo(name = "avatar_link")
    var avatarLink: String = "",
    @ColumnInfo(name = "workspace_id")
    var workspaceId: String = "",
    @ColumnInfo(name = "is_private")
    var isPrivate: Boolean = false,
    @ColumnInfo(name = "page")
    override var page: Int = 0
) : PaginatedDbModel()