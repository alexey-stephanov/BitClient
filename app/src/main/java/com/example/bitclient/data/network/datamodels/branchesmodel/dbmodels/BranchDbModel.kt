package com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bitclient.data.network.datamodels.pagingmodels.PaginatedDbModel

@Entity(tableName = "branches")
data class BranchDbModel(
    @PrimaryKey
    @ColumnInfo(name = "branch_name")
    var branchName: String = "",
    @ColumnInfo(name = "page")
    override var page: Int = 0
) : PaginatedDbModel()