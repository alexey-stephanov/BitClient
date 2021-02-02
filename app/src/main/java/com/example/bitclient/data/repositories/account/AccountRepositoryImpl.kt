package com.example.bitclient.data.repositories.account

import com.example.bitclient.data.database.UserAccountDao
import com.example.bitclient.data.di.RequestQualifier
import com.example.bitclient.data.network.datamodels.usermodel.UserModel
import com.example.bitclient.data.network.networkavailability.NetworkStatus
import com.example.bitclient.data.network.requests.RequestsApi
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    @RequestQualifier private val service: RequestsApi,
    private val userAccountDao: UserAccountDao
) : AccountRepository {

    override suspend fun retrieveUserInfoFromNetwork(): UserModel {
        val user = service.getUserInfo()
        saveUserInfoInDatabase(user)
        return user
    }

    override fun retrieveUserInfoFromDatabase(): UserModel = userAccountDao.getAllUsers()[0]

    private suspend fun saveUserInfoInDatabase(userModel: UserModel) {
        userAccountDao.insertUser(userModel)
    }
}