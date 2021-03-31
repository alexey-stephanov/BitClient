package com.example.bitclient.di.user.repositories.branches

import com.example.bitclient.di.user.repositories.branches.commits.CommitsSubcomponent
import com.example.bitclient.ui.view.fragments.BranchesFragment
import dagger.Subcomponent

@BranchesScope
@Subcomponent(modules = [BranchesModule::class])
interface BranchesSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BranchesSubcomponent
    }

    fun inject(branchesFragment: BranchesFragment)

    fun commitsSubcomponent(): CommitsSubcomponent.Factory
}