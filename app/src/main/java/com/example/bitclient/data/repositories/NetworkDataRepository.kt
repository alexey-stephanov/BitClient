package com.example.bitclient.data.repositories

import com.example.bitclient.data.models.usermodel.UserModel

interface NetworkDataRepository {
    suspend fun retrieveTokens(code: String)
    suspend fun retrieveUserInfo(): UserModel
}