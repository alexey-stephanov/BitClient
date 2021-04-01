package com.example.bitclient.data.database

import androidx.room.*
import com.example.bitclient.data.network.datamodels.accountmodel.dbmodels.AccountDbModel

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts WHERE is_active LIKE 1")
    suspend fun getActiveAccount(): AccountDbModel?

    @Query("DELETE FROM accounts WHERE account_id LIKE :accountId")
    suspend fun deleteAccount(accountId: String)

    @Query("UPDATE accounts SET is_active = 0 WHERE account_id LIKE :accountId")
    suspend fun inactiveAccount(accountId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountDbModel)

    @Update
    suspend fun update(account: AccountDbModel)

    @Query("DELETE FROM accounts")
    suspend fun clearAll()
}