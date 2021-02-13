package com.example.bitclient.data.network.networkavailability

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.bitclient.BitClientApp

object NetworkStatus : LiveData<Boolean>() {

    private lateinit var application: BitClientApp
    private lateinit var networkRequest: NetworkRequest

    fun create(application: BitClientApp) {
        this.application = application
        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        if (!isNetworkAvailable())
            postValue(false)
        getDetails()
    }

    private fun getDetails() {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    postValue(false)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }
            })
    }

    @Suppress("DEPRECATION")
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                    return true
                else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                    return true
            }
        } else {
            try {
                val activeInfo = connectivityManager.activeNetworkInfo
                if (activeInfo != null && activeInfo.isConnected)
                    return true
            } catch (e: Exception) {
                Log.e("NETWORK_AVAILABILITY", "NETWORK AVAILABILITY ERROR")
            }
        }
        return false
    }
}