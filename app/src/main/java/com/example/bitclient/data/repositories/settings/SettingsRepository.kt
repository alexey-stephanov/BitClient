package com.example.bitclient.data.repositories.settings

interface SettingsRepository {
    suspend fun logout()
    suspend fun clearCache()
}