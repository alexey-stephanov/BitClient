package com.example.bitclient.data.user.repositories

import com.example.bitclient.data.user.RecyclerViewModule
import com.example.bitclient.ui.view.fragments.BranchesFragment
import com.example.bitclient.ui.view.fragments.RepositoriesFragment
import dagger.Subcomponent

@RepositoriesScope
@Subcomponent(modules = [RepositoriesViewModelsModule::class, RecyclerViewModule::class])
interface RepositoriesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RepositoriesComponent
    }

    fun inject(repositoriesFragment: RepositoriesFragment)
    fun inject(branchesFragment: BranchesFragment)
}