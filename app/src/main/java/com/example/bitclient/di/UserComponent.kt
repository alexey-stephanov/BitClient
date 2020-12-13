package com.example.bitclient.di

import com.example.bitclient.ui.view.fragments.BranchesFragment
import com.example.bitclient.ui.view.fragments.RepositoriesFragment
import com.example.bitclient.ui.view.fragments.SettingsFragment
import dagger.Subcomponent

@AuthorizedUserScope
@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun inject(repositoriesFragment: RepositoriesFragment)
    fun inject(branchesFragment: BranchesFragment)
    fun inject(settingsFragment: SettingsFragment)
}