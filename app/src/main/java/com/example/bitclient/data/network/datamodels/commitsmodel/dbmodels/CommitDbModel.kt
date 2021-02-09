package com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

@Entity(tableName = "commits")
data class CommitDbModel(
    @ColumnInfo(name = "author_name")
    var authorName: String = "",
    @PrimaryKey
    @ColumnInfo(name = "message")
    var message: String = "",
    @ColumnInfo(name = "date")
    var date: String = "",
    @ColumnInfo(name = "page")
    override var page: Int = 0
) : PaginatedDbModel()