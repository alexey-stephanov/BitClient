package com.example.bitclient

import android.app.Application
import com.example.bitclient.di.AppComponent
import com.example.bitclient.di.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco


class BitClientApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}