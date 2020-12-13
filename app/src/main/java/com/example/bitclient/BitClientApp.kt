package com.example.bitclient

import android.app.Application
import com.example.bitclient.di.AppComponent
import com.example.bitclient.di.DaggerAppComponent


class BitClientApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}