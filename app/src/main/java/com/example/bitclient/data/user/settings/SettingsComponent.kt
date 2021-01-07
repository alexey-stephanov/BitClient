package com.example.bitclient.data.user.settings

import com.example.bitclient.ui.view.fragments.SettingsFragment
import dagger.Subcomponent

@SettingsScope
@Subcomponent(modules = [SettingsViewModelModule::class])
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsComponent
    }

    fun inject(settingsFragment: SettingsFragment)
}