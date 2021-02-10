package com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

@Entity(tableName = "commits")
data class CommitDbModel(
    @PrimaryKey
    @ColumnInfo(name = "hash")
    val commitHash: String,
    @ColumnInfo(name = "author_name")
    val authorName: String,
    @ColumnInfo(name = "message")
    val message: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "page")
    override val page: Int
) : PaginatedDbModel {
    override val unique: String
        get() = commitHash
}