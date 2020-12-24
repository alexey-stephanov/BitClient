package com.example.bitclient.data.storage

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesStorage @Inject constructor(context: Context) : Storage {
    private val sharedPreferences = context.getSharedPreferences("tokens", Context.MODE_PRIVATE)

    override fun getString(key: String): String =
            sharedPreferences.getString(key, "")!!

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
}