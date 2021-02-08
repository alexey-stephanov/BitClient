package com.example.bitclient.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel

@Dao
interface AccountDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<AccountDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg accounts: AccountDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountDbModel)

    @Query("DELETE FROM users")
    suspend fun clearAll()
}