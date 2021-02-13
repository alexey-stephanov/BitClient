package com.example.bitclient.data.di.user.repositories

import com.example.bitclient.data.di.user.repositories.branches.BranchesSubcomponent
import javax.inject.Inject

@RepositoriesScope
class BranchesSubcomponentManager @Inject constructor(private val branchesSubcomponentFactory: BranchesSubcomponent.Factory) {

    var branchesSubcomponent: BranchesSubcomponent? = null
        private set
        get() = field ?: run {
            branchesSubcomponent = branchesSubcomponentFactory.create()
            branchesSubcomponent
        }

    fun removeComponent() {
        branchesSubcomponent = null
    }
}