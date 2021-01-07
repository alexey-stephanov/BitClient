package com.example.bitclient.data.user.account

import com.example.bitclient.ui.view.fragments.AccountFragment
import dagger.Subcomponent

@AccountScope
@Subcomponent(modules = [AccountViewModelModule::class])
interface AccountComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AccountComponent
    }

    fun inject(accountFragment: AccountFragment)
}