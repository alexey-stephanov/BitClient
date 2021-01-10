package com.example.bitclient.data.di.user.settings

import com.example.bitclient.ui.view.fragments.SettingsFragment
import dagger.Subcomponent

@SettingsScope
@Subcomponent(modules = [SettingsViewModelModule::class])
interface SettingsSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsSubcomponent
    }

    fun inject(settingsFragment: SettingsFragment)
}