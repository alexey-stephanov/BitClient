package com.example.bitclient.data.network.networkavailability

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.transition.Slide
import androidx.transition.TransitionManager
import javax.inject.Inject

class NetworkConnectivityManagerImpl @Inject constructor() : NetworkConnectivityManager {

    override fun startConnectionChecking(
        viewLifecycleOwner: LifecycleOwner,
        sceneRoot: ViewGroup,
        textView: TextView
    ) {
        NetworkStatus.observe(viewLifecycleOwner, { isAvailable ->
            TransitionManager.beginDelayedTransition(sceneRoot, Slide())
            textView.isVisible = !isAvailable
        })
    }
}