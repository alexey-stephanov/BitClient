package com.example.bitclient.di.user

import com.example.bitclient.di.user.account.AccountSubcomponent
import com.example.bitclient.di.user.settings.SettingsSubcomponent
import com.example.bitclient.ui.view.fragments.HomeFragment
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class, RequestsModule::class, RefreshTokenModule::class, UserSubcomponents::class])
interface UserSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserSubcomponent
    }

    fun inject(homeFragment: HomeFragment)

    fun accountSubcomponent(): AccountSubcomponent.Factory
    fun repositoriesSubcomponentManager(): RepositoriesSubcomponentManager
    fun settingsSubcomponent(): SettingsSubcomponent.Factory
}