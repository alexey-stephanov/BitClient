package com.example.bitclient.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts")
    suspend fun getAll(): List<AccountDbModel>

    @Query("SELECT * FROM accounts WHERE account_id = :accountId")
    suspend fun getUserById(accountId: String): AccountDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountDbModel)

    @Query("DELETE FROM accounts")
    suspend fun clearAll()
}