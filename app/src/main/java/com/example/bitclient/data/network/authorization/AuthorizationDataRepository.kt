package com.example.bitclient.data.network.authorization

interface AuthorizationDataRepository {
    suspend fun retrieveTokens(code: String)
}