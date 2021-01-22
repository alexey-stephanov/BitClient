package com.example.bitclient.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.network.events.EventProducer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val eventProducer: EventProducer) : ViewModel() {

    private lateinit var job: Job

    fun handleAuthorizationCode(code: String) {
        job = GlobalScope.launch(Dispatchers.IO) {
            eventProducer.pushCode(code)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancelIfPossible()
    }

    private fun Job.cancelIfPossible() {
        if (this.isActive)
            cancel()
    }
}