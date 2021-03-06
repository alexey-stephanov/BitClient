package com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels

import androidx.room.*
import com.example.bitclient.data.network.datamodels.pagingmodel.PaginatedDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel

@Entity(
    tableName = "branches",
    foreignKeys = [ForeignKey(
        entity = RepositoryDbModel::class,
        parentColumns = ["repository_id"],
        childColumns = ["branch_owner_id"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index(value = ["branch_id"])]
)
data class BranchDbModel(
    @PrimaryKey
    @ColumnInfo(name = "branch_id")
    val branchId: String,
    @ColumnInfo(name = "branch_owner_id")
    val branchOwnerId: String,
    @ColumnInfo(name = "branch_name")
    val branchName: String,
    @ColumnInfo(name = "page")
    override val page: Int
) : PaginatedDbModel {
    override val unique: String
        get() = branchId
}