package com.example.bitclient.di

import android.content.Context
import com.example.bitclient.di.authorization.AuthorizationSubcomponent
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
        DatabaseModule::class,
        EventsModule::class,
        StorageModule::class,
        NetworkConnectivityModule::class,
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