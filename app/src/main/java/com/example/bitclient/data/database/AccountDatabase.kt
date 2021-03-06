package com.example.bitclient.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel
import com.example.bitclient.data.network.datamodels.branchesmodel.dbmodels.BranchDbModel
import com.example.bitclient.data.network.datamodels.commitsmodel.dbmodels.CommitDbModel
import com.example.bitclient.data.network.datamodels.repositoriesmodel.dbmodels.RepositoryDbModel
import com.example.bitclient.data.network.datamodels.workspacesmodel.dbmodels.WorkspaceDbModel

@Database(
    entities = [AccountDbModel::class, WorkspaceDbModel::class, RepositoryDbModel::class, BranchDbModel::class, CommitDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun workspacesDao(): WorkspacesDao
    abstract fun repositoriesDao(): RepositoriesDao
    abstract fun branchesDao(): BranchesDao
    abstract fun commitsDao(): CommitsDao
}