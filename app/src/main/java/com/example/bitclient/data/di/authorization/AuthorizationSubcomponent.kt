package com.example.bitclient.data.di.authorization

import com.example.bitclient.ui.view.fragments.AuthorizationFragment
import dagger.Subcomponent

@AuthorizationScope
@Subcomponent(modules = [AuthorizationRequestsModule::class, AuthorizationViewModelModule::class])
interface AuthorizationSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthorizationSubcomponent
    }

    fun inject(authorizationFragment: AuthorizationFragment)
}