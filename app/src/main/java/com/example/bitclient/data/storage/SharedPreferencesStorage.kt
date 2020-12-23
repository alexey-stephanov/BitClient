package com.example.bitclient.data.storage

import android.content.Context
import javax.inject.Inject

class SharedPreferencesStorage(name: String, context: Context) : Storage {
    private val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    override fun getString(key: String): String =
            sharedPreferences.getString(key, "")!!

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
}