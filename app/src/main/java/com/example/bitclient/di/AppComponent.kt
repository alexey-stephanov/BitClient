package com.example.bitclient.di

import android.content.Context
import com.example.bitclient.data.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class, ViewModelFactoryModule::class, ViewModelsModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun authorizationComponent(): AuthorizationComponent.Factory

    fun userManager(): UserManager
}