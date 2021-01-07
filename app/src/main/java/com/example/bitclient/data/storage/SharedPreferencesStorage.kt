package com.example.bitclient.data.storage

import android.content.Context
import com.example.bitclient.data.network.networkmodels.TokensModel
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

    override fun saveTokens(tokensModel: TokensModel) {
        with(sharedPreferences.edit()) {
            putString("access_token", tokensModel.accessToken)
            putString("refresh_token", tokensModel.refreshToken)
            apply()
        }
    }
}