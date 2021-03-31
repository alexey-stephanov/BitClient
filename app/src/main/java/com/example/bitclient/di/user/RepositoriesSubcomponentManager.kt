package com.example.bitclient.di.user

import com.example.bitclient.di.user.repositories.RepositoriesSubcomponent
import javax.inject.Inject

@UserScope
class RepositoriesSubcomponentManager @Inject constructor(private val repositoriesSubcomponentFactory: RepositoriesSubcomponent.Factory) {

    var repositoriesSubcomponent: RepositoriesSubcomponent? = null
        private set
        get() = field ?: run {
            repositoriesSubcomponent = repositoriesSubcomponentFactory.create()
            repositoriesSubcomponent
        }

    fun removeComponent() {
        repositoriesSubcomponent = null
    }
}