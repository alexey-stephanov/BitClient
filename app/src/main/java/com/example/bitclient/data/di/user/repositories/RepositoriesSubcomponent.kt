package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.ui.view.fragments.BranchesFragment
import com.example.bitclient.ui.view.fragments.CommitsFragment
import com.example.bitclient.ui.view.fragments.RepositoriesFragment
import dagger.Subcomponent

@RepositoriesScope
@Subcomponent(modules = [RepositoriesModule::class, PaginatedViewModelFactoryModule::class, RecyclerViewModule::class])
interface RepositoriesSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RepositoriesSubcomponent
    }

    fun inject(repositoriesFragment: RepositoriesFragment)
    fun inject(branchesFragment: BranchesFragment)
    fun inject(commitsFragment: CommitsFragment)
}