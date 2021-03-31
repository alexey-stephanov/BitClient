package com.example.bitclient.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts WHERE is_active LIKE :isActive")
    suspend fun getActiveAccount(isActive: Boolean = true): AccountDbModel

    @Query("DELETE FROM accounts WHERE account_id LIKE :accountId")
    suspend fun deleteAccount(accountId: String)

    @Query("UPDATE accounts SET is_active = :isActive WHERE account_id LIKE :accountId")
    suspend fun updateAccountFlag(accountId: String, isActive: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountDbModel)

    @Query("DELETE FROM accounts")
    suspend fun clearAll()
}