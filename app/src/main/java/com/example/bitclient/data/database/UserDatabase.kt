package com.example.bitclient.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.datamodels.usermodel.UserModel

@Database(entities = [UserModel::class, RepositoryModel::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun userRepositoriesDao(): UserRepositoriesDao
    abstract fun userAccountDao(): UserAccountDao
}