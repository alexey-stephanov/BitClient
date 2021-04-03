package com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel

@Entity(
    tableName = "commits",
    foreignKeys = [ForeignKey(
        entity = BranchDbModel::class,
        parentColumns = ["branch_id"],
        childColumns = ["commit_owner_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CommitDbModel(
    @PrimaryKey
    @ColumnInfo(name = "hash")
    val commitHash: String,
    @ColumnInfo(name = "commit_owner_id")
    val commitOwnerId: String,
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