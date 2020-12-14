package com.example.bitclient.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(): ViewModel() {

    fun sayA() {
        Log.d("TAGGG", "AuthVM is working")
    }
}