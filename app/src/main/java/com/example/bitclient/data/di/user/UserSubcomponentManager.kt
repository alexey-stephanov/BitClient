package com.example.bitclient.data.di.user

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSubcomponentManager @Inject constructor(private val userSubcomponentFactory: UserSubcomponent.Factory) {

    var userSubcomponent: UserSubcomponent? = null
        private set
        get() = field ?: run {
            userSubcomponent = userSubcomponentFactory.create()
            userSubcomponent
        }

    fun removeComponent() {
        userSubcomponent = null
    }
}