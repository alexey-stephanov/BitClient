package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.network.datamodels.usermodel.UserModel

interface AccountRepository {
    suspend fun retrieveUserInfo(): UserModel
}