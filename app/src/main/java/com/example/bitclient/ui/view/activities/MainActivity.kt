package com.example.bitclient.ui.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.bitclient.BitClientApp
import com.example.bitclient.R
import com.example.bitclient.databinding.ActivityMainBinding
import com.example.bitclient.ui.view.fragments.AuthorizationFragmentDirections
import com.example.bitclient.ui.viewmodels.MainViewModel
import com.example.bitclient.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as BitClientApp).appComponent.inject(this)

        setTheme(R.style.Theme_BitClient)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val code = intent?.data?.getQueryParameter("code")
        if (code != null) {
            mainViewModel.handleAuthorizationCode(code)
        }
    }
}