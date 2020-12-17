package com.example.bitclient.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.bitclient.data.repositories.NetworkDataRepository
import retrofit2.Retrofit
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(private val retrofit: Retrofit, networkDataRepository: NetworkDataRepository): ViewModel() {

    val url = networkDataRepository.authorizationUrl

    fun startRequest() {

    }

}