package com.example.bitclient.data.di.user.repositories.branches.commits

import com.example.bitclient.ui.view.fragments.CommitsFragment
import dagger.Subcomponent

@CommitsScope
@Subcomponent(modules = [CommitsModule::class])
interface CommitsSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CommitsSubcomponent
    }

    fun inject(commitsFragment: CommitsFragment)
}