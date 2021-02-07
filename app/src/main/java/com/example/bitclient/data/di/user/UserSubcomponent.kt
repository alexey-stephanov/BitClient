package com.example.bitclient.data.di.user

import com.example.bitclient.data.di.user.account.AccountSubcomponent
import com.example.bitclient.data.di.user.repositories.RepositoriesSubcomponent
import com.example.bitclient.data.di.user.settings.SettingsSubcomponent
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [UserModule::class, DatabaseModule::class, NetworkRepositoryModule::class, RequestsModule::class, RefreshTokenModule::class, UserSubcomponents::class])
interface UserSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserSubcomponent
    }

    fun accountComponent(): AccountSubcomponent.Factory
    fun repositoriesComponent(): RepositoriesSubcomponent.Factory
    fun settingsComponent(): SettingsSubcomponent.Factory
}