package com.example.bitclient.data.user

import javax.inject.Inject

@UserScope
class UserDataRepositoryImpl @Inject constructor(private val userManager: UserManager) :
    UserDataRepository