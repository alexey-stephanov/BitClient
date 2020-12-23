package com.example.bitclient.data.user

import com.example.bitclient.di.UserComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(
        private val userComponentFactory: UserComponent.Factory
) {
    private lateinit var displayName: String
    private lateinit var avatar: String

    var userComponent: UserComponent? = null
        private set

    fun createComponent() {
        userComponent = userComponentFactory.create()
    }

    fun loginUser(displayName: String, avatar: String) {
        this.displayName = displayName
        this.avatar = avatar
    }

    fun getUserInfo(): String = displayName

    fun logout() {
        userComponent = null
    }
}