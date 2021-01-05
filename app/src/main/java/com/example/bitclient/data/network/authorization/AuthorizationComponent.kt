package com.example.bitclient.data.network.authorization

import com.example.bitclient.ui.view.fragments.AuthorizationFragment
import com.example.bitclient.ui.viewmodels.AuthorizationViewModelModule
import dagger.Subcomponent

@AuthorizationScope
@Subcomponent(modules = [AuthorizationViewModelModule::class])
interface AuthorizationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthorizationComponent
    }

    fun inject(authorizationFragment: AuthorizationFragment)
}