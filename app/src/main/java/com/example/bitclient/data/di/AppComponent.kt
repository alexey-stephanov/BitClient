package com.example.bitclient.data.di

import android.content.Context
import com.example.bitclient.data.network.NetworkModule
import com.example.bitclient.data.network.RetrofitModule
import com.example.bitclient.data.network.authorization.AuthorizationComponent
import com.example.bitclient.data.storage.StorageModule
import com.example.bitclient.data.user.UserComponentManager
import com.example.bitclient.ui.view.activities.MainActivity
import com.example.bitclient.ui.viewmodels.MainViewModelModule
import com.example.bitclient.ui.viewmodels.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppSubcomponents::class,
        ViewModelFactoryModule::class,
        MainViewModelModule::class,
        NetworkModule::class,
        RetrofitModule::class,
        StorageModule::class],
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun authorizationComponent(): AuthorizationComponent.Factory
    fun userComponentManager(): UserComponentManager
}