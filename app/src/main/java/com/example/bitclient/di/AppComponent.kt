package com.example.bitclient.di

import android.content.Context
import com.example.bitclient.data.user.UserManager
import com.example.bitclient.ui.view.activities.MainActivity
import com.example.bitclient.ui.view.fragments.RepositoriesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class, ViewModelFactoryModule::class, ViewModelsModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun authorizationComponent(): AuthorizationComponent.Factory

    fun inject(mainActivity: MainActivity)

    fun userManager(): UserManager
}