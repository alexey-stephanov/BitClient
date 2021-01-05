package com.example.bitclient.data.storage

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.bitclient.data.network.networkmodels.TokenModel
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

    override fun saveTokens(tokenModel: TokenModel) {
        with(sharedPreferences.edit()) {
            putString("access_token", tokenModel.accessToken)
            putString("refresh_token", tokenModel.refreshToken)
            apply()
        }
    }
}