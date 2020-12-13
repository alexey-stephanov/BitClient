package com.example.bitclient.di

import com.example.bitclient.ui.view.fragments.AuthorizationFragment
import dagger.Subcomponent

@AuthorizationScope
@Subcomponent
interface AuthorizationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthorizationComponent
    }

    fun inject(authorizationFragment: AuthorizationFragment)
}