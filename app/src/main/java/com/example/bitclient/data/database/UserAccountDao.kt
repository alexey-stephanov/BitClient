package com.example.bitclient.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bitclient.data.network.datamodels.repositoriesmodel.RepositoryModel
import com.example.bitclient.data.network.datamodels.usermodel.UserModel

@Dao
interface UserAccountDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<UserModel>

    @Insert
    fun insertAllUsers(users: List<UserModel>)

    @Insert
    fun insertUser(user: UserModel)
}