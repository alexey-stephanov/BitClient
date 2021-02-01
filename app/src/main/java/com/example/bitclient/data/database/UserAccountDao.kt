package com.example.bitclient.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.usermodel.UserModel

@Dao
interface UserAccountDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<UserModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserModel)

    @Query("DELETE FROM users")
    suspend fun clearAll()
}