package com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

@Entity(tableName = "branches")
data class BranchDbModel(
    @PrimaryKey
    @ColumnInfo(name = "branch_hash")
    val branchHash: String,
    @ColumnInfo(name = "branch_name")
    val branchName: String,
    @ColumnInfo(name = "page")
    override val page: Int
) : PaginatedDbModel {
    override val unique: String
        get() = branchHash
}