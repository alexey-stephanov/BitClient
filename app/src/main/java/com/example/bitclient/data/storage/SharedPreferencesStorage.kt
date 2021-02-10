package com.example.bitclient.data.storage

import android.content.Context
import com.example.bitclient.data.network.datamodels.TokensModel
import javax.inject.Inject
import javax.inject.Singleton

private const val STORAGE_FILE_NAME = "tokens"
private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"
private const val DEFAULT_VALUE = ""

@Singleton
class SharedPreferencesStorage @Inject constructor(context: Context) : Storage {

    private val sharedPreferences = context.getSharedPreferences(STORAGE_FILE_NAME, Context.MODE_PRIVATE)

    override fun getString(key: String): String = sharedPreferences.getString(key, DEFAULT_VALUE)!!

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun saveTokens(tokensModel: TokensModel) {
        with(sharedPreferences.edit()) {
            putString(ACCESS_TOKEN_KEY, tokensModel.accessToken)
            putString(REFRESH_TOKEN_KEY, tokensModel.refreshToken)
            apply()
        }
    }

    override fun clearStorage() {
        with(sharedPreferences.edit()) {
            putString(ACCESS_TOKEN_KEY, "")
            putString(REFRESH_TOKEN_KEY, "")
            apply()
        }
    }
}