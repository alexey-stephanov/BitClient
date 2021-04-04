package com.example.bitclient.di.user.organizations

import com.example.bitclient.ui.view.fragments.OrganizationsFragment
import dagger.Subcomponent

@OrganizationsScope
@Subcomponent(modules = [OrganizationsModule::class])
interface OrganizationsSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): OrganizationsSubcomponent
    }

    fun inject(organizationsFragment: OrganizationsFragment)
}