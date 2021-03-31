package com.example.bitclient.di.user.repositories

import com.example.bitclient.ui.view.fragments.RepositoriesFragment
import dagger.Subcomponent

@RepositoriesScope
@Subcomponent(modules = [RepositoriesModule::class, RecyclerViewModule::class, RepositoriesSubcomponents::class])
interface RepositoriesSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RepositoriesSubcomponent
    }

    fun inject(repositoriesFragment: RepositoriesFragment)

    fun branchesSubcomponentManager(): BranchesSubcomponentManager
}