package com.example.bitclient.data.di

import android.content.Context
import com.example.bitclient.data.di.authorization.AuthorizationSubcomponent
import com.example.bitclient.data.di.user.UserSubcomponentManager
import com.example.bitclient.ui.view.activities.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        MainViewModelModule::class,
        BaseRetrofitModule::class,
        StorageModule::class,
        AppSubcomponents::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun authorizationSubcomponent(): AuthorizationSubcomponent.Factory
    fun userSubcomponentManager(): UserSubcomponentManager
}