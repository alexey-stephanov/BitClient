package com.example.bitclient.data.user

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserComponentManager @Inject constructor(private val userComponentFactory: UserComponent.Factory) {

    var userComponent: UserComponent? = null
        private set
        get() = field ?: run {
            userComponent = userComponentFactory.create()
            userComponent
        }

    fun removeComponent() {
        userComponent = null
    }
}