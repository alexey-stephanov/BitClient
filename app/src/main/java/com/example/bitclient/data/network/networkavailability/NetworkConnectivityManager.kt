package com.example.bitclient.data.network.networkavailability

import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner

interface NetworkConnectivityManager {
    fun startConnectionChecking(viewLifecycleOwner: LifecycleOwner, sceneRoot: ViewGroup, textView: TextView)
}