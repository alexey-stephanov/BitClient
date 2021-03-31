package com.example.bitclient.di.user.account

import com.example.bitclient.ui.view.fragments.AccountFragment
import dagger.Subcomponent

@AccountScope
@Subcomponent(modules = [AccountModule::class])
interface AccountSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AccountSubcomponent
    }

    fun inject(accountFragment: AccountFragment)
}