package com.example.bitclient.data.repositories

import com.example.bitclient.data.models.UserModel

interface NetworkDataRepository {
    suspend fun retrieveTokens(code: String)
    suspend fun refreshAccessToken()
    suspend fun retrieveUserInfo(): UserModel
}