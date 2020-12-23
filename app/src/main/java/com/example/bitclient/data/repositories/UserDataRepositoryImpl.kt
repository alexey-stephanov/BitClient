package com.example.bitclient.data.repositories

import com.example.bitclient.data.user.UserManager
import com.example.bitclient.di.UserScope
import javax.inject.Inject

@UserScope
class UserDataRepositoryImpl @Inject constructor(private val userManager: UserManager) {

}