package com.example.bitclient.data.user

import com.example.bitclient.di.UserComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(
        private val userComponentFactory: UserComponent.Factory
) {
    var userComponent: UserComponent? = null
        private set

    fun loginUser() {
        userComponent = userComponentFactory.create()
    }

    fun logout() {
        userComponent = null
    }
}