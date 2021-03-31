package com.example.bitclient.di.authorization

import com.example.bitclient.ui.view.fragments.AuthorizationFragment
import dagger.Subcomponent

@AuthorizationScope
@Subcomponent(modules = [AuthorizationRequestsModule::class, AuthorizationModule::class])
interface AuthorizationSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthorizationSubcomponent
    }

    fun inject(authorizationFragment: AuthorizationFragment)
}