package com.example.bitclient.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitclient.data.network.datamodels.usermodel.dbmodels.AccountDbModel

@Database(
    entities = [AccountDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}