package com.example.bitclient.data.user

import com.example.bitclient.data.user.account.AccountComponent
import com.example.bitclient.data.user.repositories.RepositoriesComponent
import com.example.bitclient.data.user.settings.SettingsComponent
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class, UserSubcomponents::class])
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun accountComponent(): AccountComponent.Factory
    fun repositoriesComponent(): RepositoriesComponent.Factory
    fun settingsComponent(): SettingsComponent.Factory
}