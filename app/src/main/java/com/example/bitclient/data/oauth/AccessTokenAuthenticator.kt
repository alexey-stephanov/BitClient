package com.example.bitclient.data.oauth

import android.util.Log
import com.example.bitclient.data.api.AuthApi
import com.example.bitclient.data.storage.Storage
import com.example.bitclient.di.Requests
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Requests
class AccessTokenAuthenticator @Inject constructor(private val storage: Storage) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request {
        val refreshToken = storage.getString("refresh_token")
        val newAccessToken = refreshTokens(refreshToken)
        return response.request.newBuilder().header("Authorization", "Bearer $newAccessToken").build()
    }

    private fun refreshTokens(refreshToken: String): String? {
        val newAccessToken: String?
        val client = OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        val retrofit = Retrofit.Builder()
                .baseUrl("https://bitbucket.org/")
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val refreshingService = retrofit.create(AuthApi::class.java)
        runBlocking {
            val result = refreshingService.refreshAccessToken("refresh_token", refreshToken)
            newAccessToken = result.accessToken
            storage.setString("access_token", result.accessToken)
            storage.setString("refresh_token", result.refreshToken)
            Log.e("QWER", result.accessToken)
        }
        return newAccessToken
    }
}