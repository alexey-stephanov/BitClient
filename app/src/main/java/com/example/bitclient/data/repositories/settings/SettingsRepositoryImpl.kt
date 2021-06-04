package com.example.bitclient.data.repositories.settings

import com.example.bitclient.data.database.AccountDao
import com.example.bitclient.data.storage.Storage
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val storage: Storage,
    private val accountDao: AccountDao
) : SettingsRepository {

    override suspend fun logout() {
        storage.clearStorage()
        accountDao.inactiveAccount()
    }

    override suspend fun clearCache() {
        accountDao.clearAll()
    }
}