package com.example.bitclient.data.user

import com.example.bitclient.ui.view.fragments.AccountFragment
import com.example.bitclient.ui.view.fragments.BranchesFragment
import com.example.bitclient.ui.view.fragments.RepositoriesFragment
import com.example.bitclient.ui.view.fragments.SettingsFragment
import com.example.bitclient.ui.viewmodels.UserViewModelsModule
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class, RecyclerViewModule::class, UserViewModelsModule::class])
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun inject(repositoriesFragment: RepositoriesFragment)
    fun inject(branchesFragment: BranchesFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(accountFragment: AccountFragment)
}