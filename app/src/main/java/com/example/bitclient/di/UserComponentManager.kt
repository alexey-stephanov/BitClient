package com.example.bitclient.di

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserComponentManager @Inject constructor(private val userComponentFactory: UserComponent.Factory) {

    var userComponent: UserComponent? = null
        private set

    fun createComponent() {
        userComponent = userComponentFactory.create()
    }

    fun removeComponent() {
        userComponent = null
    }
}